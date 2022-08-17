package dev.techie.buy_purchases.data.mapper

import dev.techie.buy_purchases.data.database.entity.CurrencySymbolDb
import dev.techie.buy_purchases.data.database.entity.SettingsDb
import dev.techie.buy_purchases.entity.Settings

fun SettingsDb.toEntity(selectedCurrencySymbols: List<CurrencySymbolDb>?): Settings = Settings(
    baseCurrencySymbol = baseCurrencySymbol,
    selectedCurrencySymbols = selectedCurrencySymbols?.map { it.toEntity() } ?: emptyList() 
)