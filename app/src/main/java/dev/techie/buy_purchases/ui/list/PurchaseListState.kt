package dev.techie.buy_purchases.ui.list

import dev.techie.buy_purchases.entity.Purchase
import dev.techie.buy_purchases.entity.PurchasePrice

data class PurchaseListState(
    val totalPrice: PurchasePrice = PurchasePrice(),
    val purchases: List<Purchase> = emptyList()
)
