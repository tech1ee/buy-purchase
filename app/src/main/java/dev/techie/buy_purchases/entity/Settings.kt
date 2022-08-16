package dev.techie.buy_purchases.entity

data class Settings(
    val baseCurrencySymbol: String? = null,
    val selectedCurrencySymbols: List<CurrencySymbol>
) {

    fun getSymbols(): List<String> {
        return selectedCurrencySymbols.map { it.symbol }
    }

    companion object {
        const val DEFAULT_CURRENCY_SYMBOL = "USD"
    }
}