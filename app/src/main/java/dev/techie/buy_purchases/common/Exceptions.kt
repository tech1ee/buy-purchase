package dev.techie.buy_purchases.common

class InvalidPurchaseException(message: ExceptionMessage) : Exception(message.name)

enum class ExceptionMessage {
    PURCHASE_TITLE_IS_EMPTY,
    PURCHASE_PRICE_IS_INVALID,
    PURCHASE_CURRENCY_IS_INVALID
}