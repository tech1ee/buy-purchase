package dev.techie.buy_purchases.data.mapper

import dev.techie.buy_purchases.data.database.entity.PurchaseDb
import dev.techie.buy_purchases.entity.Purchase
import dev.techie.buy_purchases.entity.PurchasePrice


fun List<PurchaseDb>.toEntity(): List<Purchase> {
    return map { it.toEntity() }
}

fun PurchaseDb.toEntity(): Purchase {
    return Purchase(
        id = this.id,
        title = this.title,
        price = PurchasePrice(
            amount = this.priceAmount,
            currencySymbol = this.priceCurrencySymbol
        ),
        categoryId = this.categoryId,
        isPurchased = this.isPurchased
    )
}

fun Purchase.toDbEntity(): PurchaseDb {
    return PurchaseDb(
        id = this.id,
        title = this.title,
        priceAmount = this.price.amount,
        priceCurrencySymbol = this.price.currencySymbol,
        categoryId = this.categoryId,
        isPurchased = this.isPurchased
    )
}