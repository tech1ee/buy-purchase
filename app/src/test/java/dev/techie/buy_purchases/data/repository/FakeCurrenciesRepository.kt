package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.entity.*
import dev.techie.buy_purchases.utils.now
import dev.techie.buy_purchases.utils.today

class FakeCurrenciesRepository(
    private val latestRates: CurrencyRates
) : CurrenciesRepository {


    override suspend fun getCurrencySymbols(refresh: Boolean): Result<List<CurrencySymbol>> {
        return Success(
            listOf(
                CurrencySymbol("USD", "US Dollar"),
                CurrencySymbol("EUR", "Euro"),
                CurrencySymbol("GBP", "British Pound"),
                CurrencySymbol("INR", "Indian Rupee"),
                CurrencySymbol("AUD", "Australian Dollar"),
                CurrencySymbol("CAD", "Canadian Dollar"),
                CurrencySymbol("SGD", "Singapore Dollar"),
                CurrencySymbol("CHF", "Swiss Franc"),
                CurrencySymbol("JPY", "Japanese Yen"),
                CurrencySymbol("CNY", "Chinese Yuan"),
                CurrencySymbol("KRW", "South Korean Won"),
                CurrencySymbol("MYR", "Malaysian Ringgit"),
                CurrencySymbol("NZD", "New Zealand Dollar"),
                CurrencySymbol("RUB", "Russian Ruble"),
                CurrencySymbol("SEK", "Swedish Krona"),
                CurrencySymbol("THB", "Thai Baht"),
                CurrencySymbol("PHP", "Philippine Peso"),
                CurrencySymbol("BRL", "Brazilian Real"),
                CurrencySymbol("MXN", "Mexican Peso"),
                CurrencySymbol("ZAR", "South African Rand"),
                CurrencySymbol("PLN", "Polish Zloty"),
                CurrencySymbol("TRY", "Turkish Lira"),
                CurrencySymbol("NOK", "Norwegian Krone"),
                CurrencySymbol("RMB", "Chinese Yuan"),
                CurrencySymbol("IDR", "Indonesian Rupiah"),
                CurrencySymbol("ILS", "Israeli Shekel"),
                CurrencySymbol("AED", "Emirati Dirham"),
                CurrencySymbol("KWD", "Kuwaiti Dinar"),
                CurrencySymbol("QAR", "Qatari Riyal"),
                CurrencySymbol("SAR", "Saudi Riyal"),
                CurrencySymbol("BHD", "Bahraini Dinar"),
                CurrencySymbol("OMR", "Omani Rial"),
                CurrencySymbol("KES", "Kenyan Shilling"),
                CurrencySymbol("TND", "Tunisian Dinar")
            )
        )
    }

    override suspend fun getCurrencyLatestRates(
        refresh: Boolean,
        symbols: List<String>,
        base: String
    ): Result<CurrencyRates> {
        return Success(latestRates)
    }
}