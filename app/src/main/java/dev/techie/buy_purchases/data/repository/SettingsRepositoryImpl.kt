package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.data.database.CurrenciesDao
import dev.techie.buy_purchases.data.database.SettingsDao
import dev.techie.buy_purchases.data.mapper.toEntity
import dev.techie.buy_purchases.domain.SettingsRepository
import dev.techie.buy_purchases.entity.Settings

class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao,
    private val currenciesDao: CurrenciesDao
): SettingsRepository {

    override suspend fun getSettings(): Settings {
        return settingsDao.getSettings().toEntity(currenciesDao.getSelectedCurrencySymbols())
    }
}