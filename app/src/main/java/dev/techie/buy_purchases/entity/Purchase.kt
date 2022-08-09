package dev.techie.buy_purchases.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Purchase(
    val title: String,
    val price: Int,
    val currencyId: Int,
    val categoryId: Int,
    val isPurchased: Boolean = false,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        const val PURCHASE_ID = "purchaseId"
    }
}
