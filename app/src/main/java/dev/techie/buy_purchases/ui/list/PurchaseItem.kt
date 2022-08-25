package dev.techie.buy_purchases.ui.list

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.techie.buy_purchases.entity.Purchase
import dev.techie.buy_purchases.ui.ContentDescription

@Composable
fun PurchaseItem(
    purchase: Purchase,
    onEditClick: (purchase: Purchase) -> Unit,
    onDoneClick: (purchase: Purchase) -> Unit,
    onDeleteClick: (purchase: Purchase) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        var isMenuVisible by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .animateContentSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable { isMenuVisible = !isMenuVisible },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp,
                pressedElevation = 10.dp
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .animateContentSize()
            ) {
                Text(
                    text = purchase.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${purchase.price.amount.toInt()} ${purchase.price.currencySymbol}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                AnimatedVisibility(
                    visible = isMenuVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically(),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = { onEditClick(purchase) },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = ContentDescription.EDIT
                                )
                            }
                        )
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = { onDoneClick(purchase) },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    contentDescription = ContentDescription.DONE
                                )
                            }
                        )
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = { onDeleteClick(purchase) },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = ContentDescription.DELETE
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}