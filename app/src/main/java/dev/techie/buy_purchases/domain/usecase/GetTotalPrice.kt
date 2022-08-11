package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.SettingsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTotalPrice @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke() {
        TODO("not implemented")
    }
}