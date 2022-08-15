package dev.techie.buy_purchases.data.repository

import dev.techie.buy_purchases.domain.SettingsRepository
import dev.techie.buy_purchases.entity.Settings

class FakeSettingsRepository(
    val settings: Settings
): SettingsRepository {
    override suspend fun getSettings(): Settings {
          return settings
    }
}