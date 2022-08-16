package dev.techie.buy_purchases.data.database

import androidx.room.*
import dev.techie.buy_purchases.data.database.entity.CurrencyRateDb
import dev.techie.buy_purchases.data.database.entity.CurrencySymbolDb

@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencySymbols(symbols: List<CurrencySymbolDb>)

    @Query("SELECT * FROM currencysymboldb")
    suspend fun getCurrencySymbols(): List<CurrencySymbolDb>

    @Query("SELECT * FROM currencysymboldb WHERE isSelected == 1")
    suspend fun getSelectedCurrencySymbols(): List<CurrencySymbolDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyRates(rates: List<CurrencyRateDb>)

    @Query("SELECT * FROM currencyratedb")
    suspend fun getCurrencyRates(): List<CurrencyRateDb>
}