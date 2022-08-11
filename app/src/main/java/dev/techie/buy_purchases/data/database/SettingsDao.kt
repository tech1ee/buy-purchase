package dev.techie.buy_purchases.data.database

import androidx.room.Dao
import androidx.room.Query
import dev.techie.buy_purchases.entity.Settings

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings")
    fun getSettings(): Settings
}