package com.mfa.accesibilitytrip.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
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

@Composable
fun FavoriteTripsContent(
    favoriteTrips: List<TripCardUiModel>,
    onOpenTrip: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onOpenCatalog: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
                        MaterialTheme.colorScheme.background,
                    ),
                ),
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            HeaderBlock()
            Spacer(modifier = Modifier.height(16.dp))

            if (favoriteTrips.isEmpty()) {
                EmptyFavoritesState(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            } else {
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
                    contentPadding = PaddingValues(bottom = 96.dp),
                ) {
                    itemsIndexed(
                        items = favoriteTrips,
                        key = { _, trip -> trip.id },
                    ) { index, trip ->
                        TripOverviewCard(
                            trip = trip,
                            index = index,
                            onClick = { onOpenTrip(trip.id) },
                            action = {
                                FavoritePlanetButton(
                                    trip = trip,
                                    onToggleFavorite = { onToggleFavorite(trip.id) },
                                )
                            },
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onOpenCatalog,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 20.dp),
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_search),
                contentDescription = stringResource(R.string.open_catalog_action),
            )
        }
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

@Composable
private fun HeaderBlock() {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.size(72.dp),
                contentAlignment = Alignment.Center,
            ) {
                PlanetLogo(modifier = Modifier.fillMaxSize())
            }
            Text(
                text = stringResource(R.string.home_title),
                modifier = Modifier.semantics { heading() },
                style = MaterialTheme.typography.displaySmall,
            )
        }

        Text(
            text = stringResource(R.string.home_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun EmptyFavoritesState(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.empty_favorites_title),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.empty_favorites_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
