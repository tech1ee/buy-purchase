package dev.techie.buy_purchases.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.techie.buy_purchases.domain.usecase.DeletePurchase
import dev.techie.buy_purchases.domain.usecase.GetPurchases
import dev.techie.buy_purchases.domain.usecase.GetTotalPrice
import dev.techie.buy_purchases.domain.usecase.UpdatePurchase
import dev.techie.buy_purchases.entity.Purchase
import dev.techie.buy_purchases.entity.PurchasePrice
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseListViewModel @Inject constructor(
    private val getPurchases: GetPurchases,
    private val getTotalPrice: GetTotalPrice,
    private val updatePurchase: UpdatePurchase,
    private val deletePurchase: DeletePurchase
) : ViewModel() {

    private val _state = mutableStateOf(PurchaseListState())
    val state: State<PurchaseListState> get() = _state

    private var getPurchasesJob: Job? = null
    private var getTotalPriceJob: Job? = null

    init {
        getAllPurchases()
    }

    fun delete(purchase: Purchase) {
        purchase.id?.let { purchaseId ->
            viewModelScope.launch {
                deletePurchase(purchaseId)
            }
        }
    }

    fun setPurchased(purchase: Purchase) {
        viewModelScope.launch {
            updatePurchase(purchase.copy(isPurchased = true))
        }
    }

    private fun getAllPurchases() {
        getPurchasesJob?.cancel()
        getPurchasesJob = getPurchases()
            .map { purchases -> purchases.filter { !it.isPurchased } }
            .onEach { purchases ->
                _state.value = _state.value.copy(
                    purchases = purchases
                )
                getTotalPurchasePrice(purchases)
            }
            .launchIn(viewModelScope)
    }

    private fun getTotalPurchasePrice(purchases: List<Purchase>) {
        getTotalPriceJob?.cancel()
        getTotalPriceJob = viewModelScope.launch {
            getTotalPrice.calculate(purchases).let { result ->
                if (result.isSuccess) _state.value = _state.value.copy(
                    totalPrice = result.getOrDefault(PurchasePrice())
                )
            }
        }
    }

    override fun onCleared() {
        getPurchasesJob?.cancel()
        getTotalPriceJob?.cancel()
        super.onCleared()
    }
}