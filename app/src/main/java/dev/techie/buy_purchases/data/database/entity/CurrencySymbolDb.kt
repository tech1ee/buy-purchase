package dev.techie.buy_purchases.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencySymbolDb(
    val currency: String,
    @PrimaryKey val symbol: String,
    val isSelected: Boolean = false
)
