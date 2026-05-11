package com.mfa.accesibilitytrip.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
fun CatalogScreen(
    tripsViewModel: TripsViewModel,
    onBack: () -> Unit,
    onOpenTrip: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CatalogContent(
        trips = tripsViewModel.catalogTrips,
        onBack = onBack,
        onOpenTrip = onOpenTrip,
        onToggleFavorite = tripsViewModel::toggleFavorite,
        modifier = modifier,
    )
}

@Composable
fun CatalogContent(
    trips: List<TripCardUiModel>,
    onBack: () -> Unit,
    onOpenTrip: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopTextBar(
                title = stringResource(R.string.catalog_title),
                onBack = onBack,
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        ),
                    ),
                )
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.catalog_subtitle, trips.size),
                modifier = Modifier.semantics { heading() },
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics {
                        collectionInfo = CollectionInfo(
                            rowCount = trips.size,
                            columnCount = 1,
                        )
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 28.dp),
            ) {
                itemsIndexed(
                    items = trips,
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
}

@Composable
internal fun TopTextBar(
    title: String,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        TextButton(onClick = onBack) {
            Text(text = stringResource(R.string.back_action))
        }
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CatalogContentPreview() {
    DSTheme {
        CatalogContent(
            trips = TripsViewModel().catalogTrips.take(8),
            onBack = {},
            onOpenTrip = {},
            onToggleFavorite = {},
        )
    }
}
