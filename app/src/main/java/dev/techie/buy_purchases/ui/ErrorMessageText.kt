package dev.techie.buy_purchases.ui

import android.content.Context
import dev.techie.buy_purchases.R
import dev.techie.buy_purchases.common.ExceptionMessage

fun String?.text(context: Context): String {
    return when (this) {
        ExceptionMessage.PURCHASE_TITLE_IS_EMPTY.name -> context.getString(R.string.error_purchase_title_is_empty)
        ExceptionMessage.PURCHASE_PRICE_IS_INVALID.name -> context.getString(R.string.error_purchase_price_is_invalid)
        ExceptionMessage.PURCHASE_CURRENCY_IS_INVALID.name -> context.getString(R.string.error_purchase_currency_is_invalid)
        else -> context.getString(R.string.error_unknown)
    }
}