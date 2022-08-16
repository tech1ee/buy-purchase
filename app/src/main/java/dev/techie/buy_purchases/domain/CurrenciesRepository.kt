package dev.techie.buy_purchases.domain

import dev.techie.buy_purchases.entity.CurrencyRates
import dev.techie.buy_purchases.entity.CurrencySymbol

interface CurrenciesRepository {

    suspend fun getCurrencySymbols(refresh: Boolean = false): Result<List<CurrencySymbol>>

    suspend fun getCurrencyLatestRates(symbols: List<String>, base: String, refresh: Boolean = false): Result<CurrencyRates>
}