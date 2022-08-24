package dev.techie.buy_purchases.ui.list

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.techie.buy_purchases.R
import dev.techie.buy_purchases.ui.ContentDescription
import dev.techie.buy_purchases.ui.Screen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PurchasesListScreen(
    navController: NavController,
    viewModel: PurchaseListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${stringResource(R.string.purchases)}: ${state.purchases.size}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = stringResource(R.string.total_) +
                                    " ${state.totalPrice.amount.toInt()} ${state.totalPrice.currencySymbol}",
                            style = MaterialTheme.typography.titleMedium
                        )
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
                    navController.navigate(Screen.PurchaseEditorScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
        ) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
            ) {
                //TODO: Add the order Section here
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.purchases) { purchase ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            when (it) {
                                DismissValue.DismissedToStart -> viewModel.delete(purchase)
                                DismissValue.DismissedToEnd -> viewModel.setPurchased(purchase)
                                else -> Unit
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss

                            val color by animateColorAsState(
                                targetValue = when (direction) {
                                    DismissDirection.EndToStart -> Color.Red
                                    DismissDirection.StartToEnd -> Color.Green
                                }
                            )

                            val icon = when (direction) {
                                DismissDirection.EndToStart -> Icons.Default.Delete
                                DismissDirection.StartToEnd -> Icons.Default.Done
                            }

                            val scale by animateFloatAsState(
                                targetValue = when (direction) {
                                    DismissDirection.EndToStart -> 0.8f
                                    DismissDirection.StartToEnd -> 1.2f
                                }
                            )

                            val alignment = when (direction) {
                                DismissDirection.EndToStart -> Alignment.CenterEnd
                                DismissDirection.StartToEnd -> Alignment.CenterStart
                            }

                            val contentDescription = when (direction) {
                                DismissDirection.EndToStart -> ContentDescription.DELETE
                                DismissDirection.StartToEnd -> ContentDescription.DONE
                            }

                            Box(modifier = Modifier.fillMaxSize()
                                .background(color)
                                .padding(16.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    imageVector = icon,
                                    modifier = Modifier.scale(scale),
                                    contentDescription = contentDescription
                                )
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                        dismissContent = {
                            PurchaseItem(
                                purchase = purchase,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(
                                            Screen.PurchaseEditorScreen.route +
                                                    "?purchaseId=${purchase.id}"
                                        )
                                    }
                            )
                        },
//                        dismissThresholds = { direction ->
//                            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
//                        }
                    )
                }
            }
        }
    }
}