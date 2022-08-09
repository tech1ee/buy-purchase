package dev.techie.buy_purchases.ui

sealed class Screen(val route: String) {
    object PurchasesListScreen: Screen("purchases_list")
    object PurchaseEditorScreen: Screen("purchase_editor")
}
