package dev.techie.buy_purchases.ui.list

import dev.techie.buy_purchases.entity.Purchase

data class PurchaseListState(
    val totalPrice: Int = 0,
    val purchases: List<Purchase> = emptyList()
)
