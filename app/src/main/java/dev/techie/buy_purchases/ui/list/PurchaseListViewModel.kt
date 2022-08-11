package dev.techie.buy_purchases.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.techie.buy_purchases.domain.usecase.GetAllCurrencySymbols
import dev.techie.buy_purchases.domain.usecase.GetPurchases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PurchaseListViewModel @Inject constructor(
    private val getAllCurrencySymbols: GetAllCurrencySymbols,
    private val getPurchases: GetPurchases
) : ViewModel() {

    private val _state = mutableStateOf(PurchaseListState())
    val state: State<PurchaseListState> get() = _state

    private var getPurchasesJob: Job? = null

    init {
        getAllCurrencySymbols()
        getAllPurchases()
    }

    private fun getAllCurrencySymbols() {
        getAllCurrencySymbols().launchIn(viewModelScope)
    }

    private fun getAllPurchases() {
        getPurchasesJob?.cancel()
        getPurchasesJob = getPurchases()
            .onEach { purchases ->
                _state.value = _state.value.copy(
                    purchases = purchases,
                    totalPrice = purchases.sumOf { it.price }
                )
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        getPurchasesJob?.cancel()
        super.onCleared()
    }
}