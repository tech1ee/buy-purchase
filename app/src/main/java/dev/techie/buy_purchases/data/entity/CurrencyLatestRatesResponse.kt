package dev.techie.buy_purchases.data.entity

data class CurrencyLatestRatesResponse(
    val base: String?,
    val date: String?,
    val rates: Map<String, Double>?,
    val success: Boolean?,
    val timestamp: Long?
)
