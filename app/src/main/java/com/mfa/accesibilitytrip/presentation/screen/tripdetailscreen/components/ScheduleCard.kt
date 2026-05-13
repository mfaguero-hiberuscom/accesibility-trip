package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.designsystem.HankenGrotesk
import com.mfa.accesibilitytrip.presentation.designsystem.Manrope
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.preview.PreviewData
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews

@Composable
internal fun ScheduleCard(trip: TripCardUiModel) {
    val extendedColors = DSThemeDefaults.extendedColors

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
        ) {
            // Departure / Rocket+Duration / Arrival row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                // Departure column
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = stringResource(R.string.detail_departure_label),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = HankenGrotesk,
                            fontWeight = FontWeight.Normal,
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "08:45",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                        ),
                        color = extendedColors.priceColor,
                    )
                    Text(
                        text = "St Pancras Int'l",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Normal,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = trip.nextDeparture,
                        style = MaterialTheme.typography.bodyMedium,
                        color = extendedColors.mutedText,
                    )
                }

                // Center: rocket icon + duration
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 24.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_rocket),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = extendedColors.priceColor,
                    )
                    Text(
                        text = trip.duration,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = extendedColors.mutedText,
                    )
                    HorizontalDivider(
                        modifier = Modifier.width(48.dp),
                        color = MaterialTheme.colorScheme.outline,
                    )
                }

                // Arrival column
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = stringResource(R.string.detail_arrival_label),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = HankenGrotesk,
                            fontWeight = FontWeight.Normal,
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "12:01",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                        ),
                        color = extendedColors.priceColor,
                    )
                    Text(
                        text = "Gare du Nord",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Normal,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = trip.nextDeparture,
                        style = MaterialTheme.typography.bodyMedium,
                        color = extendedColors.mutedText,
                    )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outline)

            // Passenger / Cohete / Asiento row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                DetailInfoItem(
                    label = stringResource(R.string.detail_passenger_label),
                    value = "e320",
                )
                DetailInfoItem(
                    label = stringResource(R.string.detail_rocket_label),
                    value = "09",
                )
                DetailInfoItem(
                    label = stringResource(R.string.detail_seat_label),
                    value = "14A",
                    valueColor = extendedColors.priceColor,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun ScheduleCardPreview() {
    DSTheme {
        ScheduleCard(trip = PreviewData.favoriteTrip)
    }
}
