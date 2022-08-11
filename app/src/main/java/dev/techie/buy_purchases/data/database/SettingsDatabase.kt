package dev.techie.buy_purchases.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.techie.buy_purchases.entity.Settings

@Database(
    entities = [Settings::class],
    version = 1
)
abstract class SettingsDatabase: RoomDatabase() {

    abstract fun settingsDao(): SettingsDao

    companion object {
        const val DATABASE_NAME = "settings_db"
    }
}