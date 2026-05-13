package com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.preview.PreviewData
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews
import com.mfa.accesibilitytrip.presentation.screen.commoncomponents.TravelTypeBadge

@Composable
internal fun TripOverviewCard(
    trip: TripCardUiModel,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    index: Int? = null,
    action: (@Composable () -> Unit)? = null,
    swipeToDelete: () -> Unit = {}
) {
    val extendedColors = DSThemeDefaults.extendedColors
    val cardModifier = modifier
        .fillMaxWidth()
        .then(
            if (index == null) {
                Modifier
            } else {
                Modifier.semantics {
                    collectionItemInfo = CollectionItemInfo(
                        rowIndex = index,
                        rowSpan = 1,
                        columnIndex = 0,
                        columnSpan = 1,
                    )

                    customActions = listOf(
                        androidx.compose.ui.semantics.CustomAccessibilityAction(
                            label = "Desliza para borrar",
                            action = {
                                swipeToDelete()
                                true
                            }
                        )
                    )

                }
            },
        )

    val cardContent: @Composable () -> Unit = {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Top row: rocket icon + badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                // Rocket icon in yellow circle
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_rocket),
                        contentDescription = stringResource(R.string.label_vehicle),
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                TravelTypeBadge(travelType = trip.travelType)
            }

            // Trip name and vehicle
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = stringResource(
                        R.string.trip_route_title,
                        trip.origin,
                        trip.destination,
                    ),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = trip.vehicleName,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            // Divider + departure/price row
            Column {
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.label_next_departure),
                            style = MaterialTheme.typography.labelMedium,
                            color = extendedColors.mutedText,
                        )
                        Text(
                            text = trip.nextDeparture,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                    Text(
                        text = trip.price,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                        ),
                        color = extendedColors.priceColor,
                    )
                }
            }

            if (action != null) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    action()
                }
            }
        }
    }

    if (onClick == null) {
        Surface(
            modifier = cardModifier,
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        ) {
            cardContent()
        }
    } else {
        Card(
            onClick = onClick,
            modifier = cardModifier,
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        ) {
            cardContent()
        }
    }
}

@ThemePreviews
@Composable
private fun TripOverviewCardPreview() {
    DSTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TripOverviewCard(
                trip = PreviewData.favoriteTrip,
                onClick = {},
            )
            TripOverviewCard(
                trip = PreviewData.regularTrip,
                onClick = null,
            )
        }
    }
}
