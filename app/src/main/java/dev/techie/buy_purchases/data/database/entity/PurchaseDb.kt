package dev.techie.buy_purchases.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PurchaseDb(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val priceAmount: Double,
    val priceCurrencySymbol: String,
    val categoryId: Int,
    val isPurchased: Boolean
) {
    companion object {
        const val PURCHASE_ID = "purchaseId"
    }
}
