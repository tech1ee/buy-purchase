package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.domain.SettingsRepository
import dev.techie.buy_purchases.entity.Purchase
import dev.techie.buy_purchases.entity.PurchasePrice
import dev.techie.buy_purchases.entity.Settings
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetTotalPrice @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val currenciesRepository: CurrenciesRepository
) {

    suspend fun calculate(purchases: List<Purchase>): Result<PurchasePrice> {
        val settings = settingsRepository.getSettings()

        val baseCurrencySymbol = settings.baseCurrencySymbol

        val currencyRatesResult = currenciesRepository.getCurrencyLatestRates(
            base = baseCurrencySymbol,
            symbols = settings.getSymbols()
        )

        if (currencyRatesResult.isSuccess) {
            var totalAmount = 0.0

            purchases.forEach { purchase ->
                val amount = purchase.price.amount
                val rate = if (purchase.price.currencySymbol != baseCurrencySymbol) {
                    currencyRatesResult.getOrNull()?.rates?.find {
                        it.symbol == purchase.price.currencySymbol
                    }?.rate ?: 0.0
                } else null

                totalAmount += if (rate != null) {
                    amount * rate
                } else amount
            }

            return Result.success(
                    PurchasePrice(
                        currencySymbol = baseCurrencySymbol,
                        amount = totalAmount
                    )
                )
        } else {
            return Result.failure(currencyRatesResult.exceptionOrNull() ?: Exception())
        }
    }
}