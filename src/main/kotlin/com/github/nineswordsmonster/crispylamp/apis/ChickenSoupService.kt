package com.github.nineswordsmonster.crispylamp.apis

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChickenSoupService {

    @GET("/oilPriceAction.do")
    suspend fun getPrice( @Query("provinceId") int: Int ): Response
}