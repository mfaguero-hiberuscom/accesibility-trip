package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components.BoardingPassCard
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components.DetailTopBar
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components.EmptyDetailState
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components.FavoritePlanetButton
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components.HeroSection
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components.ScheduleCard
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
private fun TripDetailContent(
    trip: TripCardUiModel?,
    onBack: () -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (trip == null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            DetailTopBar(onBack = onBack)
            EmptyDetailState()
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            // Back button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Surface(
                    onClick = onBack,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Transparent,
                    modifier = Modifier.size(44.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.back_action),
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }

            // Hero with margins
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                HeroSection(trip = trip)
            }

            // Cards section
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Schedule card
                ScheduleCard(trip = trip)

                // Boarding pass
                BoardingPassCard()

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Pinned favorite button at bottom
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars),
            ) {
                FavoritePlanetButton(
                    trip = trip,
                    onToggleFavorite = { onToggleFavorite(trip.id) },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                )
            }
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
