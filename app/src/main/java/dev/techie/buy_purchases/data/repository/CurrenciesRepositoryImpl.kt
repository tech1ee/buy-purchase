package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.data.api.CurrenciesApi
import dev.techie.buy_purchases.data.database.CurrenciesDao
import dev.techie.buy_purchases.data.database.entity.CurrencyRateDb
import dev.techie.buy_purchases.data.entity.UnsuccessfulResponseException
import dev.techie.buy_purchases.data.mapper.toDbEntity
import dev.techie.buy_purchases.data.mapper.toEntity
import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.entity.*
import dev.techie.buy_purchases.utils.now
import dev.techie.buy_purchases.utils.today

class CurrenciesRepositoryImpl(
    private val currenciesDao: CurrenciesDao,
    private val api: CurrenciesApi
) : CurrenciesRepository {

    override suspend fun getCurrencySymbols(refresh: Boolean): Result<List<CurrencySymbol>> {
        val symbolsFromDatabase = currenciesDao.getCurrencySymbols()

        return if (symbolsFromDatabase.isEmpty() || refresh) fetchCurrencySymbols()
        else Success(symbolsFromDatabase.map { it.toEntity() })
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
                        currenciesDao.insertCurrencySymbols(it.map { symbol -> symbol.toDbEntity() })
                        Success(it)
                    }
            } else {
                Failure(UnsuccessfulResponseException())
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getCurrencyLatestRates(
        refresh: Boolean,
        symbols: List<String>,
        base: String
    ): Result<CurrencyRates> {
        val ratesFromDatabase = currenciesDao.getCurrencyRates()

        return when {
            ratesFromDatabase.isEmpty() ||
                    !ratesFromDatabase.all { it.base == base && it.date != today() } ||
                    refresh -> fetchCurrencyLatestRates(symbols, base)
            else -> Success(
                CurrencyRates(
                    base = base,
                    date = ratesFromDatabase.first().date,
                    timestamp = ratesFromDatabase.first().timestamp,
                    rates = ratesFromDatabase.map { CurrencyRate(it.symbol, it.rate) }
                )
            )
        }
    }

    private suspend fun fetchCurrencyLatestRates(
        symbols: List<String>,
        base: String
    ): Result<CurrencyRates> {
        return try {
            val response = api.getCurrencyLatestRates(symbols, base)
            val data = response.body()

            if (response.isSuccessful &&
                data?.success == true &&
                !data.rates.isNullOrEmpty()
            ) {
                currenciesDao.insertCurrencyRates(
                    data.rates.toDbEntity(
                        base = base,
                        date = data.date ?: today(),
                        timestamp = data.timestamp ?: now()
                    )
                )
                Success(
                    CurrencyRates(
                        base = base,
                        date = data.date ?: today(),
                        timestamp = data.timestamp ?: now(),
                        rates = data.rates.map { CurrencyRate(it.key, it.value) }
                    )
                )
            } else {
                Failure(UnsuccessfulResponseException())
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private fun List<CurrencyRateDb>.isValid(baseCurrency: Currency) =
        all { it.base == baseCurrency.symbol }
}