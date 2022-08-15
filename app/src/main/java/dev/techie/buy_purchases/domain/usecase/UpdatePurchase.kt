package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.common.ExceptionMessage
import dev.techie.buy_purchases.common.InvalidPurchaseException
import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.entity.Purchase

class UpdatePurchase(
    private val purchaseRepository: PurchasesRepository
) {
    suspend operator fun invoke(purchase: Purchase) {
        if (purchase.title.isEmpty()) throw InvalidPurchaseException(ExceptionMessage.PURCHASE_TITLE_IS_EMPTY)
        if (purchase.price.amount < 0) throw InvalidPurchaseException(ExceptionMessage.PURCHASE_PRICE_IS_INVALID)

        purchaseRepository.updatePurchase(purchase)
    }
}