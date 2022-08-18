package dev.techie.buy_purchases.domain.usecase

import dev.techie.buy_purchases.data.fakeCurrencyRates
import dev.techie.buy_purchases.data.repository.FakeCurrenciesRepository
import dev.techie.buy_purchases.data.repository.FakePurchasesRepository
import dev.techie.buy_purchases.data.repository.FakeSettingsRepository
import dev.techie.buy_purchases.entity.*
import dev.techie.buy_purchases.utils.now
import dev.techie.buy_purchases.utils.today
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetTotalPriceTest {

    @Test
    fun `should return total price of purchase`() {

        val baseCurrencySymbol = "USD"

        val purchases = listOf(
            Purchase(
                id = 1,
                title = "Donut",
                price = PurchasePrice(
                    currencySymbol = "USD",
                    amount = 1.0, // = 1.0
                ),
                categoryId = 0
            ),
            Purchase(
                id = 2,
                title = "Coffee",
                price = PurchasePrice(
                    currencySymbol = "CAD",
                    amount = 2.0, // * 1.42 = 2.84,
                ),
                categoryId = 0
            ),
            Purchase(
                id = 3,
                title = "Tea",
                price = PurchasePrice(
                    currencySymbol = "GBP",
                    amount = 3.0, // * 0.89 = 2.67
                ),
                categoryId = 0
            ),
            Purchase(
                id = 4,
                title = "Coffee",
                price = PurchasePrice(
                    currencySymbol = "EUR",
                    amount = 1.5 // * 1.11 = 1.665
                ),
                categoryId = 0
            )
        ) // total: 8.175
        val currencyRates = getCurrencyRates(base = baseCurrencySymbol)

        val settingsRepository =
            FakeSettingsRepository(
                Settings(
                    baseCurrencySymbol = baseCurrencySymbol,
                    selectedCurrencySymbols = listOf(
                        CurrencySymbol("", "USD"),
                        CurrencySymbol("", "CAD"),
                        CurrencySymbol("", "GBP"),
                        CurrencySymbol("", "EUR")
                    )
                )
            )
        val currenciesRepository = FakeCurrenciesRepository(currencyRates)
        val purchasesRepository = FakePurchasesRepository()

        val getTotalPrice = GetTotalPrice(
            settingsRepository = settingsRepository,
            currenciesRepository = currenciesRepository,
        )

        runBlocking {
            purchases.forEach { purchasesRepository.addPurchase(it) }

            val totalPrice: Double = getTotalPrice.calculate(purchases).getOrNull()?.amount ?: 0.0

            assertEquals(8.175, totalPrice, 0.0)
        }
    }


    private fun getCurrencyRates(base: String, rates: List<CurrencyRate>? = null) = CurrencyRates(
        base = base,
        date = today(),
        rates = rates?.toMutableList()?.apply { removeIf { it.symbol == base } }
            ?: fakeCurrencyRates.apply { removeIf { it.symbol == base } },
        timestamp = now()
    )
}

