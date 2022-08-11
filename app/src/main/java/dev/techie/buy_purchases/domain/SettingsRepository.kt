package dev.techie.buy_purchases.domain

import dev.techie.buy_purchases.entity.Settings

interface SettingsRepository {

    suspend fun getSettings(): Settings
}