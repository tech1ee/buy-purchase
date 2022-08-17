package dev.techie.buy_purchases.entity

data class Settings(
    val baseCurrencySymbol: String = DEFAULT_CURRENCY_SYMBOL,
    val selectedCurrencySymbols: List<CurrencySymbol> = emptyList()
) {

    fun getSymbols(): List<String> {
        return selectedCurrencySymbols.map { it.symbol }
    }

    companion object {
        const val DEFAULT_CURRENCY_SYMBOL = "USD"
    }
}