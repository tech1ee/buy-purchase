package dev.techie.buy_purchases.domain

import dev.techie.buy_purchases.entity.Purchase
import kotlinx.coroutines.flow.Flow

interface PurchasesRepository {

    fun getAllPurchases(): Flow<List<Purchase>>

    suspend fun getPurchase(id: Int): Purchase?

    suspend fun addPurchase(purchase: Purchase): Purchase

    suspend fun updatePurchase(purchase: Purchase)

    suspend fun deletePurchase(id: Int)

}