package com.github.nineswordsmonster.crispylamp.apis

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface OilSoupService {

    @GET("/oilPriceAction.do")
    suspend fun getPrice( @Query("provinceId") int: Int ): ResponseBody
}
