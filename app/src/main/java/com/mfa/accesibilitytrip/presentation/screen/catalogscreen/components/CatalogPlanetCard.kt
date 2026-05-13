package com.mfa.accesibilitytrip.presentation.screen.catalogscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.preview.PreviewData
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews

@Composable
internal fun CatalogPlanetCard(
    trip: TripCardUiModel,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
) {
    val extendedColors = DSThemeDefaults.extendedColors

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = extendedColors.catalogCardBg,
        border = BorderStroke(1.dp, extendedColors.catalogCardBorder),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Planet image placeholder
            PlanetImagePlaceholder(
                planetName = trip.destination,
                modifier = Modifier
                    .size(112.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .semantics {
                        contentDescription = trip.destination
                    },
            )

            // Trip info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = trip.destination,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    IconButton(
                        onClick = onToggleFavorite,
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            modifier = Modifier.size(44.dp),
                            painter = painterResource(
                                if (trip.isFavorite) R.drawable.ic_heart_filled
                                else R.drawable.ic_heart_outline
                            ),
                            contentDescription = if (trip.isFavorite) {
                                stringResource(R.string.remove_favorite_action, trip.origin, trip.destination)
                            } else {
                                stringResource(R.string.add_favorite_action, trip.origin, trip.destination)
                            },
                            tint = if (trip.isFavorite) Color(0xFFFF6B6B)
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.catalog_travel_info),
                    style = MaterialTheme.typography.labelLarge,
                    color = extendedColors.subtitleAccent,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = trip.price,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                        ),
                        color = extendedColors.priceColor,
                    )
                    Button(
                        onClick = onClick,
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.catalog_reserve),
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun CatalogPlanetCardPreview() {
    DSTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CatalogPlanetCard(
                trip = PreviewData.favoriteTrip,
                onClick = {},
                onToggleFavorite = {},
            )
            CatalogPlanetCard(
                trip = PreviewData.regularTrip,
                onClick = {},
                onToggleFavorite = {},
            )
        }
    }
}
