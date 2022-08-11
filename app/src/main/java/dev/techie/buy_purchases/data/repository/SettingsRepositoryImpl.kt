package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.data.database.SettingsDao
import dev.techie.buy_purchases.domain.SettingsRepository
import dev.techie.buy_purchases.entity.Settings

class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao
): SettingsRepository {

    override suspend fun getSettings(): Settings {
        return settingsDao.getSettings()
    }
}