package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.PurchasesRepository
import javax.inject.Inject

class GetPurchase @Inject constructor(
    private val repository: PurchasesRepository
) {

    suspend operator fun invoke(id: Int) = repository.getPurchase(id)
}