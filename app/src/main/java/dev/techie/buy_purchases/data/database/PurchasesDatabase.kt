package dev.techie.buy_purchases.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.techie.buy_purchases.data.database.entity.PurchaseDb

@Database(
    entities = [PurchaseDb::class],
    version = 1
)
abstract class PurchasesDatabase : RoomDatabase() {

    abstract fun purchasesDao(): PurchasesDao

    companion object {
        const val DATABASE_NAME = "purchases_db"
    }
}