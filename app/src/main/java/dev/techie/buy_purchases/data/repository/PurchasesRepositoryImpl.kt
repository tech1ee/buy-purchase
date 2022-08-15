package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.data.database.PurchasesDao
import dev.techie.buy_purchases.data.mapper.toDbEntity
import dev.techie.buy_purchases.data.mapper.toEntity
import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.entity.Purchase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PurchasesRepositoryImpl(
    private val purchasesDao: PurchasesDao
) : PurchasesRepository {

    override fun getAllPurchases(): Flow<List<Purchase>> {
        return purchasesDao.getAllPurchases().map { it.toEntity() }
    }

    override suspend fun getPurchase(id: Int): Purchase? {
        return purchasesDao.getPurchase(id)?.toEntity()
    }

    override suspend fun addPurchase(purchase: Purchase): Purchase {
        TODO("Not yet implemented")
    }

    override suspend fun updatePurchase(purchase: Purchase) {
        purchasesDao.insert(purchase.toDbEntity())
    }

    override suspend fun deletePurchase(id: Int): Boolean {
        TODO("Not yet implemented")
    }

}