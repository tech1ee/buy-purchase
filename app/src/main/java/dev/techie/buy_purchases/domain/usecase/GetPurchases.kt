package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.PurchasesRepository
import javax.inject.Inject

class GetPurchases @Inject constructor(
    private val purchasesRepository: PurchasesRepository
) {

    operator fun invoke() = purchasesRepository.getAllPurchases()
}