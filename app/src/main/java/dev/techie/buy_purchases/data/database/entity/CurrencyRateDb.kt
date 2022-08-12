package dev.techie.buy_purchases.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyRateDb(
    // Combined symbols of base currency and this [USDEUR]
    @PrimaryKey val id: String,
    val base: String,
    val symbol: String,
    val date: String,
    val timestamp: Long,
    val rate: Double
)