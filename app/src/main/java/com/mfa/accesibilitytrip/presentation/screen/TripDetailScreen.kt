package com.mfa.accesibilitytrip.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.viewmodel.TripsViewModel

@Composable
fun TripDetailScreen(
    tripsViewModel: TripsViewModel,
    tripId: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TripDetailContent(
        trip = tripsViewModel.findTrip(tripId),
        onBack = onBack,
        onToggleFavorite = tripsViewModel::toggleFavorite,
        modifier = modifier,
    )
}

@Composable
fun TripDetailContent(
    trip: TripCardUiModel?,
    onBack: () -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopTextBar(
                title = stringResource(R.string.detail_title),
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
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.42f),
                        ),
                    ),
                )
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            if (trip == null) {
                EmptyDetailState()
            } else {
                TripOverviewCard(
                    trip = trip,
                    onClick = null,
                )
                FavoritePlanetButton(
                    trip = trip,
                    onToggleFavorite = { onToggleFavorite(trip.id) },
                )
                DetailBlock(
                    title = stringResource(R.string.detail_manifest_title),
                    body = buildString {
                        append(stringResource(R.string.label_vehicle))
                        append(": ")
                        append(trip.vehicleName)
                        append("\n")
                        append(stringResource(R.string.label_duration))
                        append(": ")
                        append(trip.duration)
                    },
                )
                DetailBlock(
                    title = stringResource(R.string.detail_accessibility_note_title),
                    body = stringResource(R.string.detail_accessibility_note_description),
                )
            }
        }
    }
}

@Composable
private fun EmptyDetailState() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.detail_not_found),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(R.string.detail_not_found_description),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun DetailBlock(
    title: String,
    body: String,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = title,
                modifier = Modifier.semantics { heading() },
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TripDetailContentPreview() {
    DSTheme {
        TripDetailContent(
            trip = TripsViewModel().favoriteTrips.first(),
            onBack = {},
            onToggleFavorite = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TripDetailMissingContentPreview() {
    DSTheme {
        TripDetailContent(
            trip = null,
            onBack = {},
            onToggleFavorite = {},
        )
    }
}
