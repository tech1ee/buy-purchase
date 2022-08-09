package dev.techie.buy_purchases.ui.editor

sealed class PurchaseEditorAction {
    data class TitleChanged(val title: String) : PurchaseEditorAction()
    data class PriceChanged(val price: Int) : PurchaseEditorAction()
    object SavePurchase : PurchaseEditorAction()
}
