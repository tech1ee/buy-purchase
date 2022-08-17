package dev.techie.buy_purchases.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.techie.buy_purchases.entity.Settings

@Entity
data class SettingsDb(
    @PrimaryKey val id: Int = 1,
    val baseCurrencySymbol: String = Settings.DEFAULT_CURRENCY_SYMBOL
)
