package dev.techie.buy_purchases.ui.editor

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import dev.techie.buy_purchases.R
import dev.techie.buy_purchases.ui.ContentDescription
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PurchaseEditorScreen(
    navController: NavController,
    viewModel: PurchaseEditorViewModel = hiltViewModel()
) {
    val purchaseState = viewModel.purchaseState.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PurchaseEditorEvent.PurchaseSaved -> {
                    navController.navigateUp()
                }
                is PurchaseEditorEvent.ShowSnackbar -> {
                    //TODO: show snackbar
                }
            }
        }
    }

    ProvideWindowInsets(
        windowInsetsAnimationsEnabled = true
    ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 16.dp),
                            text = stringResource(R.string.edit_purchase),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null) {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = ContentDescription.BACK
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .shadow(elevation = 8.dp)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onAction(PurchaseEditorAction.SavePurchase)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = ContentDescription.SAVE
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                OutlinedTextField(
                    value = purchaseState.title,
                    onValueChange = {
                        viewModel.onAction(PurchaseEditorAction.TitleChanged(it))
                    },
                    label = { Text(stringResource(R.string.title)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
                OutlinedTextField(
                    value = if (purchaseState.price <= 0) "" else purchaseState.price.toString(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    label = { Text(stringResource(R.string.price)) },
                    onValueChange = {
                        viewModel.onAction(PurchaseEditorAction.PriceChanged(it.toIntOrNull() ?: 0))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}