package dev.techie.buy_purchases.domain

import dev.techie.buy_purchases.entity.CurrencyRates
import dev.techie.buy_purchases.entity.CurrencySymbol
import dev.techie.buy_purchases.entity.Result

interface CurrenciesRepository {

    suspend fun getCurrencySymbols(refresh: Boolean = false): Result<List<CurrencySymbol>>

    suspend fun getCurrencyLatestRates(refresh: Boolean, symbols: List<String>, base: String): Result<CurrencyRates>
}