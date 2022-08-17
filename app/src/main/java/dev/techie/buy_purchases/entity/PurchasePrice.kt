package dev.techie.buy_purchases.entity

data class PurchasePrice(
    val currencySymbol: String = Settings.DEFAULT_CURRENCY_SYMBOL,
    val amount: Double = 0.0
)
