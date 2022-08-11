package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.data.database.PurchasesDao
import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.entity.Purchase
import kotlinx.coroutines.flow.Flow

class PurchasesRepositoryImpl(
    private val purchasesDao: PurchasesDao
) : PurchasesRepository {

    override fun getAllPurchases(): Flow<List<Purchase>> {
        return purchasesDao.getAllPurchases()
    }

    override suspend fun getPurchase(id: Int): Purchase? {
        return purchasesDao.getPurchase(id)
    }

    override suspend fun addPurchase(purchase: Purchase): Purchase {
        TODO("Not yet implemented")
    }

    override suspend fun updatePurchase(purchase: Purchase) {
        purchasesDao.insert(purchase)
    }

    override suspend fun deletePurchase(id: String): Boolean {
        TODO("Not yet implemented")
    }

}