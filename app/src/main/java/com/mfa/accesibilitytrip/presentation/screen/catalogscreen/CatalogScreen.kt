package com.mfa.accesibilitytrip.presentation.screen.catalogscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.mfa.accesibilitytrip.presentation.screen.catalogscreen.components.CatalogPlanetCard
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
private fun CatalogContent(
    trips: List<TripCardUiModel>,
    onBack: () -> Unit,
    onOpenTrip: (String) -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Top bar with back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Surface(
                onClick = onBack,
                shape = RoundedCornerShape(12.dp),
                color = Color.Transparent,
                modifier = Modifier.size(40.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(R.string.back_action),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Text(
                text = stringResource(R.string.catalog_title),
                modifier = Modifier.semantics { heading() },
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
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
                CatalogPlanetCard(
                    trip = trip,
                    onClick = { onOpenTrip(trip.id) },
                    onToggleFavorite = { onToggleFavorite(trip.id) },
                )
            }
        }
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
