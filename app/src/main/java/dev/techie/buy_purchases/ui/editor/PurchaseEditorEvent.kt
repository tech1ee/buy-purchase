package dev.techie.buy_purchases.ui.editor

sealed class PurchaseEditorEvent {
    data class ShowSnackbar(val message: String?) : PurchaseEditorEvent()
    object PurchaseSaved : PurchaseEditorEvent()
}
