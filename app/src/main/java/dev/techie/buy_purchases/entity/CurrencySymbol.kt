package dev.techie.buy_purchases.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencySymbol(
    @PrimaryKey val code: String,
    val symbol: String
)
