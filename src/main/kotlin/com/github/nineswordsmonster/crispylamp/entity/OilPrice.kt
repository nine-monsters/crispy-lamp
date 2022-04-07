package com.github.nineswordsmonster.crispylamp.entity

data class OilPrice(
    val location: String,
    val startDate: String = "",
    val endDate: String = "",
    val prices: MutableMap<String, MutableList<Price>> = mutableMapOf()
) {
}

data class Price(val type: String, val price: String)