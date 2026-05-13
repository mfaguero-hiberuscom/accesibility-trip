package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.preview.PreviewData
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews

@Composable
fun FavoritePlanetButton(
    trip: TripCardUiModel,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme
    val containerColor = if (trip.isFavorite) {
        colors.primary
    } else {
        colors.primary
    }
    val contentColor = colors.onPrimary
    val labelRes = if (trip.isFavorite) {
        R.string.favorite_action_selected
    } else {
        R.string.favorite_action_unselected
    }
    val announcement = stringResource(
        if (trip.isFavorite) {
            R.string.remove_favorite_action
        } else {
            R.string.add_favorite_action
        },
        trip.origin,
        trip.destination,
    )
    val state = stringResource(
        if (trip.isFavorite) {
            R.string.favorite_state_on
        } else {
            R.string.favorite_state_off
        },
    )

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onToggleFavorite)
            .semantics {
                role = Role.Button
                contentDescription = announcement
                stateDescription = state
            },
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(
                    id = if (trip.isFavorite) {
                        R.drawable.ic_heart_filled
                    } else {
                        R.drawable.ic_heart_outline
                    },
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = contentColor,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(labelRes),
                style = MaterialTheme.typography.titleMedium,
                color = contentColor,
            )
        }
    }
}

@ThemePreviews
@Composable
private fun FavoritePlanetButtonPreview() {
    DSTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            FavoritePlanetButton(
                trip = PreviewData.favoriteTrip,
                onToggleFavorite = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            )
            FavoritePlanetButton(
                trip = PreviewData.regularTrip,
                onToggleFavorite = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
            )
        }
    }
}
