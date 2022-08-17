package dev.techie.buy_purchases.data.database

import androidx.room.Dao
import androidx.room.Query
import dev.techie.buy_purchases.data.database.entity.SettingsDb

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settingsdb")
    suspend fun getSettings(): SettingsDb?
}