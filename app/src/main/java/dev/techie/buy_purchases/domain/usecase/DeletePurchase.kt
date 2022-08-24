package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.PurchasesRepository

class DeletePurchase(
    private val purchasesRepository: PurchasesRepository
) {

    suspend operator fun invoke(id: Int) = purchasesRepository.deletePurchase(id)
}