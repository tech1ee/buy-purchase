package dev.techie.buy_purchases.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.techie.buy_purchases.entity.CurrencySymbol

@Database(
    entities = [CurrencySymbol::class],
    version = 1
)
abstract class CurrenciesDatabase: RoomDatabase() {

    abstract fun currenciesDao(): CurrenciesDao

    companion object {
        const val DATABASE_NAME = "currencies_db"
    }

}