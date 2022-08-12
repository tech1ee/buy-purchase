package dev.techie.buy_purchases.data.mapper

import dev.techie.buy_purchases.data.database.entity.CurrencyRateDb
import dev.techie.buy_purchases.data.database.entity.CurrencySymbolDb
import dev.techie.buy_purchases.entity.CurrencySymbol

fun CurrencySymbolDb.toEntity() = CurrencySymbol(
    currency = currency,
    symbol = symbol
)

fun CurrencySymbol.toDbEntity() = CurrencySymbolDb(
    currency = currency,
    symbol = symbol
)

fun Map<String, Double>.toDbEntity(
    base: String,
    date: String,
    timestamp: Long
): List<CurrencyRateDb> = map {
    CurrencyRateDb(
        id = "$base${it.key}",
        base = base,
        symbol = it.key,
        date = date,
        timestamp = timestamp,
        rate = it.value
    )
}