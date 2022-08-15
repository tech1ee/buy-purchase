package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.entity.Purchase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePurchasesRepository: PurchasesRepository {

    private val purchases = arrayListOf<Purchase>()

    override fun getAllPurchases(): Flow<List<Purchase>> {
        return flow { emit(purchases) }
    }

    override suspend fun getPurchase(id: Int): Purchase? {
        return purchases.find { it.id == id }
    }

    override suspend fun addPurchase(purchase: Purchase): Purchase {
        purchases.add(purchase)
        return purchase
    }

    override suspend fun updatePurchase(purchase: Purchase) {
        val index = purchases.indexOfFirst { it.id == purchase.id }
        purchases[index] = purchase
    }

    override suspend fun deletePurchase(id: Int): Boolean {
        purchases.removeAll { it.id == id }
        return true
    }
}