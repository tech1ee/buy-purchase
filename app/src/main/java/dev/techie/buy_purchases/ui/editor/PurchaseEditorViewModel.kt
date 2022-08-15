package dev.techie.buy_purchases.ui.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.techie.buy_purchases.common.InvalidPurchaseException
import dev.techie.buy_purchases.domain.usecase.GetPurchase
import dev.techie.buy_purchases.domain.usecase.UpdatePurchase
import dev.techie.buy_purchases.entity.Purchase
import dev.techie.buy_purchases.entity.PurchasePrice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseEditorViewModel @Inject constructor(
    private val getPurchase: GetPurchase,
    private val updatePurchase: UpdatePurchase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _purchaseState = mutableStateOf(PurchaseEditorState())
    val purchaseState: State<PurchaseEditorState> get() = _purchaseState

    private val _eventFlow = MutableSharedFlow<PurchaseEditorEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPurchaseId: Int? = null

    init {
        savedStateHandle.get<Int>(Purchase.PURCHASE_ID)?.let { purchaseId ->
            if (purchaseId > 0) {
               viewModelScope.launch {
                   getPurchase(purchaseId)?.also { purchase ->
                       currentPurchaseId = purchase.id
                       _purchaseState.value = purchaseState.value.copy(
                           title = purchase.title,
                           price = purchase.price.amount.toInt()
                       )
                   }
               }
            }
        }
    }

    fun onAction(action: PurchaseEditorAction) {
        when (action) {
            is PurchaseEditorAction.TitleChanged -> {
                _purchaseState.value = purchaseState.value.copy(title = action.title)
            }
            is PurchaseEditorAction.PriceChanged -> {
                _purchaseState.value = purchaseState.value.copy(price = action.price)
            }
            is PurchaseEditorAction.SavePurchase -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        updatePurchase(
                            Purchase(
                                id = currentPurchaseId,
                                title = purchaseState.value.title,
                                price = PurchasePrice(
                                    currencySymbol = "USD",
                                    amount = purchaseState.value.price.toDouble()
                                ),
                                categoryId = 0
                            )
                        )
                        _eventFlow.emit(PurchaseEditorEvent.PurchaseSaved)
                    } catch (e: InvalidPurchaseException) {
                        _eventFlow.emit(PurchaseEditorEvent.ShowSnackbar(e.message))
                    }
                }
            }
        }
    }
}