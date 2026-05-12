package com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components.EmptyFavoritesState
import com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components.HeaderBlock
import com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components.SwipeHintCard
import com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components.SwipeToDismissCard
import com.mfa.accesibilitytrip.presentation.viewmodel.TripsViewModel

@Composable
fun FavoriteTripsScreen(
    tripsViewModel: TripsViewModel,
    onOpenTrip: (String) -> Unit,
    onOpenCatalog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FavoriteTripsContent(
        favoriteTrips = tripsViewModel.favoriteTrips,
        onOpenTrip = onOpenTrip,
        onToggleFavorite = tripsViewModel::toggleFavorite,
        onOpenCatalog = onOpenCatalog,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteTripsContent(
    favoriteTrips: List<TripCardUiModel>,
    onOpenTrip: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onOpenCatalog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var tripToDelete by remember { mutableStateOf<TripCardUiModel?>(null) }

    // Confirmation dialog
    tripToDelete?.let { trip ->
        AlertDialog(
            onDismissRequest = { tripToDelete = null },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_heart_filled),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color(0xFFFF6B6B),
                )
            },
            title = {
                Text(
                    text = stringResource(R.string.remove_action),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            text = {
                Text(
                    text = stringResource(
                        R.string.remove_favorite_action,
                        trip.origin,
                        trip.destination,
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onToggleFavorite(trip.id)
                        tripToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF6B6B),
                        contentColor = Color.White,
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.confirm_remove),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { tripToDelete = null },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Text(
                        text = stringResource(R.string.cancel_action),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            },
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderBlock(favoriteCount = favoriteTrips.size)
        Spacer(modifier = Modifier.height(24.dp))

        if (favoriteTrips.isEmpty()) {
            EmptyFavoritesState(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        } else {
            // Swipe hint card
            SwipeHintCard()
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .semantics {
                        collectionInfo = CollectionInfo(
                            rowCount = favoriteTrips.size,
                            columnCount = 1,
                        )
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 16.dp),
            ) {
                itemsIndexed(
                    items = favoriteTrips,
                    key = { _, trip -> trip.id },
                ) { index, trip ->
                    SwipeToDismissCard(
                        trip = trip,
                        index = index,
                        onClick = { onOpenTrip(trip.id) },
                        onSwipeToDelete = { tripToDelete = trip },
                    )
                }
            }
        }

        // CTA button
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onOpenCatalog,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            Text(
                text = stringResource(R.string.open_catalog_action),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
    }
}



@Preview(showBackground = true)
@Composable
private fun FavoriteTripsContentPreview() {
    DSTheme {
        FavoriteTripsContent(
            favoriteTrips = TripsViewModel().favoriteTrips,
            onOpenTrip = {},
            onToggleFavorite = {},
            onOpenCatalog = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteTripsEmptyContentPreview() {
    DSTheme {
        FavoriteTripsContent(
            favoriteTrips = emptyList(),
            onOpenTrip = {},
            onToggleFavorite = {},
            onOpenCatalog = {},
        )
    }
}
