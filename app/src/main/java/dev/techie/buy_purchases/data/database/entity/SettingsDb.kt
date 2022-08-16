package dev.techie.buy_purchases.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingsDb(
    @PrimaryKey val id: Int = 1,
    val baseCurrencySymbol: String? = null
)
