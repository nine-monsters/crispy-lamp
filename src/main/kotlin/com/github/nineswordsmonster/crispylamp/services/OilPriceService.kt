package com.github.nineswordsmonster.crispylamp.services

import com.github.nineswordsmonster.crispylamp.apis.ApiClient
import com.github.nineswordsmonster.crispylamp.entity.Location
import com.github.nineswordsmonster.crispylamp.entity.OilPrice
import com.github.nineswordsmonster.crispylamp.entity.Price
import com.intellij.openapi.diagnostic.logger
import kotlinx.coroutines.*
import org.jsoup.Jsoup

private val LOG = logger<OilPriceService>()

class OilPriceService {
    private fun getOilPrices(htmlStr: String): OilPrice {
        val html = Jsoup.parse(htmlStr)
        val box = html.getElementsByClass("pri_box")

        val title = box.select(".top_box > h3").text()
        val date = box.select(".top_box > p").text()
        val location = box.select(".top_box > .right > span").text()
        // 汽油柴油
        val oils = box.select(".center_box:not(:last-child)")

        val oilPrice = OilPrice(location, date.split("-")[0], date.split("-")[1])
        oils.forEachIndexed { index, element ->

            val oil = element.select(".left_box span").text()
            val priceList = arrayListOf<Price>()
            oilPrice.prices[oil] = priceList

            val prices = element.select(".right_box")
            val t_list = prices.select(".top_ul li")
            val p_list = prices.select(".bottom_ul li")
            for ((index2, t) in t_list.withIndex()) {
                val ts = t.select("li")
                val ps = p_list[index2].select("li")
                for ((idx2, _) in ts.withIndex()) {
                    LOG.debug("${title} --> ${date} --> ${location} --> ${oil} --> ${ts[idx2].text()}: ${ps[idx2].text()}")
                    priceList.add(Price(ts[idx2].text(), ps[idx2].text()))
                }
            }
        }

        return oilPrice
    }

    private fun getLocations(htmlStr: String): List<Location> {
        val regex = Regex("queryOilById\\((.*)\\)")
        val html = Jsoup.parse(htmlStr)
        val box = html.getElementsByClass("city_list")

        val los = box.select("ul li")
        val locations = mutableListOf<Location>().apply {
            los.forEach {
                val id = regex.matchEntire(it.attr("onclick"))?.groupValues?.get(1)
                id?.let { it1 -> add(Location(it1.toInt(), it.select("span").text())) }
            }
        }
        return locations
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getOilPrice() = CoroutineScope(Dispatchers.Default).async { ApiClient.oilSoupService.getPrice(32) }

    fun getOil(): OilPrice {
        val soup = OilPriceService().getOilPrice()
        return runBlocking {
            val htmlStr = soup.await().string()
            LOG.debug("The answer is $htmlStr")
            val res = getOilPrices(htmlStr)
            LOG.info(res.toString())
            res
        }
    }

    fun getLocations(): List<Location> {
        val soup = OilPriceService().getOilPrice()
        return runBlocking {
            val htmlStr = soup.await().string()
            LOG.debug("The answer is $htmlStr")
            val res = getLocations(htmlStr)
            LOG.info(res.toString())
            res
        }
    }
}
