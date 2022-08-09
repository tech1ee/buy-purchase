package dev.techie.buy_purchases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.techie.buy_purchases.ui.Screen
import dev.techie.buy_purchases.ui.editor.PurchaseEditorScreen
import dev.techie.buy_purchases.ui.list.PurchasesListScreen
import dev.techie.buy_purchases.ui.theme.BuypurchasesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuypurchasesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PurchasesListScreen.route
                    ) {
                        composable(route = Screen.PurchasesListScreen.route) {
                            PurchasesListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.PurchaseEditorScreen.route +
                                    "?purchaseId={purchaseId}",
                            arguments = listOf(
                                navArgument("purchaseId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            PurchaseEditorScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}