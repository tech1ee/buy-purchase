package dev.techie.buy_purchases.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings(
    @PrimaryKey val id: Int = 1,
    val baseCurrencySymbol: String? = null
)