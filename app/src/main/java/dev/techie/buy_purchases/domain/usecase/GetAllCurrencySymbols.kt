package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.domain.CurrenciesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllCurrencySymbols @Inject constructor(
    private val currencyRepository: CurrenciesRepository
) {

    operator fun invoke() = flow {
        emit(currencyRepository.getCurrencySymbols())
    }
}