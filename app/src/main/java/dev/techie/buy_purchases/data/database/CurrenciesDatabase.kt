package dev.techie.buy_purchases.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.techie.buy_purchases.data.database.entity.CurrencyRateDb
import dev.techie.buy_purchases.data.database.entity.CurrencySymbolDb

@Database(
    entities = [CurrencySymbolDb::class, CurrencyRateDb::class],
    version = 1
)
abstract class CurrenciesDatabase: RoomDatabase() {

    abstract fun currenciesDao(): CurrenciesDao

    companion object {
        const val DATABASE_NAME = "currencies_db"
    }

}