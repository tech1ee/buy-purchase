package dev.techie.buy_purchases.entity

import dev.techie.buy_purchases.data.database.entity.PurchaseDb


data class Purchase(
    val id: Int?,
    val title: String,
    val price: PurchasePrice,
    val categoryId: Int,
    val isPurchased: Boolean = false
) {
    companion object {
        const val PURCHASE_ID = PurchaseDb.PURCHASE_ID
    }
}
