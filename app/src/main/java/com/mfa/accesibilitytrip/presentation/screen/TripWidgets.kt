package com.mfa.accesibilitytrip.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.model.TravelTypeUiModel
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel

@Composable
fun PlanetLogo(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Canvas(modifier = modifier) {
        val ringHeight = size.minDimension * 0.18f
        val ringWidth = size.minDimension * 0.95f
        val ringOffset = Offset(
            x = size.width * 0.08f,
            y = size.height * 0.45f,
        )

        rotate(degrees = -18f, pivot = center) {
            drawRoundRect(
                color = colors.tertiary,
                topLeft = ringOffset,
                size = Size(ringWidth, ringHeight),
                cornerRadius = CornerRadius(ringHeight, ringHeight),
            )
        }

        drawCircle(
            color = colors.secondary,
            radius = size.minDimension * 0.26f,
            center = Offset(size.width * 0.45f, size.height * 0.5f),
        )

        drawCircle(
            color = colors.surface,
            radius = size.minDimension * 0.1f,
            center = Offset(size.width * 0.35f, size.height * 0.4f),
        )

        drawCircle(
            color = colors.primary.copy(alpha = 0.75f),
            radius = size.minDimension * 0.05f,
            center = Offset(size.width * 0.56f, size.height * 0.58f),
        )
    }
}

@Composable
fun TripOverviewCard(
    trip: TripCardUiModel,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    index: Int? = null,
    action: (@Composable () -> Unit)? = null,
) {
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
                }
            },
        )

    val cardContent: @Composable () -> Unit = {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = stringResource(
                            R.string.trip_route_title,
                            trip.origin,
                            trip.destination,
                        ),
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(
                        text = trip.vehicleName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                TravelTypeBadge(travelType = trip.travelType)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                JourneyFact(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.label_next_departure),
                    value = trip.nextDeparture,
                )
                JourneyFact(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.label_price),
                    value = trip.price,
                )
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
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
        ) {
            cardContent()
        }
    } else {
        Card(
            onClick = onClick,
            modifier = cardModifier,
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            cardContent()
        }
    }
}

@Composable
fun FavoritePlanetButton(
    trip: TripCardUiModel,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme
    val containerColor = if (trip.isFavorite) {
        colors.primary.copy(alpha = 0.16f)
    } else {
        Color.Transparent
    }
    val borderColor = if (trip.isFavorite) {
        colors.primary
    } else {
        colors.outline
    }
    val contentColor = if (trip.isFavorite) {
        colors.primary
    } else {
        colors.onSurfaceVariant
    }
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
            .clip(RoundedCornerShape(100.dp))
            .clickable(onClick = onToggleFavorite)
            .semantics {
                role = Role.Button
                contentDescription = announcement
                stateDescription = state
            },
        shape = RoundedCornerShape(100.dp),
        color = containerColor,
        border = BorderStroke(1.dp, borderColor),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(
                    id = if (trip.isFavorite) {
                        R.drawable.ic_planet_favorite_filled
                    } else {
                        R.drawable.ic_planet_favorite_outline
                    },
                ),
                contentDescription = null,
                tint = contentColor,
            )
            Text(
                text = stringResource(labelRes),
                style = MaterialTheme.typography.labelLarge,
                color = contentColor,
            )
        }
    }
}

@Composable
private fun TravelTypeBadge(
    travelType: TravelTypeUiModel,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.colorScheme
    val containerColor = if (travelType == TravelTypeUiModel.HYPERLUMINOUS) {
        colors.secondary.copy(alpha = 0.18f)
    } else {
        colors.tertiary.copy(alpha = 0.24f)
    }
    val iconColor = if (travelType == TravelTypeUiModel.HYPERLUMINOUS) {
        colors.secondary
    } else {
        colors.tertiary
    }
    val initial = if (travelType == TravelTypeUiModel.HYPERLUMINOUS) "H" else "N"

    Surface(
        modifier = modifier,
        color = containerColor,
        shape = RoundedCornerShape(100.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(iconColor),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = initial,
                    color = colors.onSecondary,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            Text(
                text = stringResource(travelType.labelRes),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun JourneyFact(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
