package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.data.api.CurrenciesApi
import dev.techie.buy_purchases.data.database.CurrenciesDao
import dev.techie.buy_purchases.data.entity.UnsuccessfulResponseException
import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.entity.CurrencySymbol
import dev.techie.buy_purchases.entity.Failure
import dev.techie.buy_purchases.entity.Result
import dev.techie.buy_purchases.entity.Success

class CurrenciesRepositoryImpl(
    private val currenciesDao: CurrenciesDao,
    private val api: CurrenciesApi
) : CurrenciesRepository {

    override suspend fun getCurrencySymbols(refresh: Boolean = false): Result<List<CurrencySymbol>> {
        val symbolsFromDatabase = currenciesDao.getCurrencySymbols()

        return if (symbolsFromDatabase.isEmpty() || refresh) fetchCurrencySymbols()
        else Success(symbolsFromDatabase)
    }

    override suspend fun getCurrencyLatestRates(
        symbols: List<String>,
        base: String
    ): Result<Map<String, Double>> {
        return try {
            val response = api.getCurrencyLatestRates(symbols, base)
            val data = response.body()

            if (response.isSuccessful &&
                data?.success == true &&
                !data.rates.isNullOrEmpty()
            ) {
                Success(data.rates)
            } else {
                Failure(UnsuccessfulResponseException())
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun fetchCurrencySymbols(): Result<List<CurrencySymbol>> {
        return try {
            val response = api.getCurrencySymbols()
            val data = response.body()

            if (response.isSuccessful &&
                data?.success == true &&
                !data.symbols.isNullOrEmpty()
            ) {
                data.symbols
                    .map { CurrencySymbol(it.key, it.value) }
                    .let {
                        currenciesDao.insertCurrencySymbols(it)
                        Success(it)
                    }
            } else {
                Failure(UnsuccessfulResponseException())
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }

}