package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.domain.SettingsRepository
import dev.techie.buy_purchases.entity.PurchasePrice
import dev.techie.buy_purchases.entity.Settings
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetTotalPrice @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val currenciesRepository: CurrenciesRepository,
    private val getPurchases: GetPurchases

) {

    private val _totalPriceFlow = MutableSharedFlow<Result<PurchasePrice>>()
    val totalPriceFlow: SharedFlow<Result<PurchasePrice>> get() = _totalPriceFlow

    suspend fun invoke() {
        val settings = settingsRepository.getSettings()

        val baseCurrencySymbol = settings.baseCurrencySymbol ?: Settings.DEFAULT_CURRENCY_SYMBOL

        val currencyRatesResult = currenciesRepository.getCurrencyLatestRates(
            base = baseCurrencySymbol,
            symbols = settings.getSymbols()
        )

        if (currencyRatesResult.isSuccess) {
            var totalAmount = 0.0

            getPurchases().collect { purchases ->
                purchases.forEach { purchase ->
                    val amount = purchase.price.amount
                    val rate = if (purchase.price.currencySymbol != baseCurrencySymbol) {
                        currencyRatesResult.getOrNull()?.rates?.find {
                            it.symbol == purchase.price.currencySymbol
                        }?.rate ?: 0.0
                    } else 0.0

                    totalAmount += amount * rate
                }
            }

            _totalPriceFlow.emit(
                Result.success(
                    PurchasePrice(
                        currencySymbol = baseCurrencySymbol,
                        amount = totalAmount
                    )
                )
            )
        } else {
            _totalPriceFlow.emit(
                Result.failure(currencyRatesResult.exceptionOrNull() ?: Exception())
            )
            return
        }
    }
}