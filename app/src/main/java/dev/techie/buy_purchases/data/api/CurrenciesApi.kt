package dev.techie.buy_purchases.data.api

import dev.techie.buy_purchases.data.entity.CurrencyLatestRatesResponse
import dev.techie.buy_purchases.data.entity.CurrencySymbolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("symbols")
    suspend fun getCurrencySymbols(): Response<CurrencySymbolsResponse>

    @GET("latest")
    suspend fun getCurrencyLatestRates(
        @Query("symbols") symbols: List<String>,
        @Query("base") base: String
    ): Response<CurrencyLatestRatesResponse>
}