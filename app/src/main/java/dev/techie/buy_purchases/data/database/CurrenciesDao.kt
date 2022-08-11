package dev.techie.buy_purchases.data.database

import androidx.room.*
import dev.techie.buy_purchases.entity.CurrencySymbol
@Dao
interface CurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencySymbols(symbols: List<CurrencySymbol>)

    @Query("SELECT * FROM currencysymbol")
    suspend fun getCurrencySymbols(): List<CurrencySymbol>
}