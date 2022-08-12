package dev.techie.buy_purchases.entity

data class CurrencyRates(
    val base: String,
    val date: String,
    val timestamp: Long,
    val rates: List<CurrencyRate>
)
