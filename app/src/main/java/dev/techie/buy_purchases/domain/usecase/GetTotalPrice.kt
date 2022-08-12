package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.domain.SettingsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTotalPrice @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val currenciesRepository: CurrenciesRepository,
    private val getPurchases: GetPurchases

) {

    operator fun invoke() {
        // Get the total price of all purchases
    }
}