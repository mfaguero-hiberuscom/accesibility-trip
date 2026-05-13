package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.designsystem.HankenGrotesk
import com.mfa.accesibilitytrip.presentation.designsystem.Manrope
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel
import com.mfa.accesibilitytrip.presentation.preview.PreviewData
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews
import com.mfa.accesibilitytrip.presentation.screen.commoncomponents.TravelTypeBadge

@Composable
internal fun HeroSection(trip: TripCardUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(12.dp)),
    ) {
        // Space background
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1a1a2e),
                        Color(0xFF16213e),
                        Color(0xFF0f3460),
                    ),
                ),
            )
            val starCount = 50
            for (i in 0 until starCount) {
                val x = size.width * ((i * 97 + 31) % 100) / 100f
                val y = size.height * ((i * 73 + 17) % 100) / 100f
                drawCircle(
                    color = Color.White.copy(alpha = 0.3f + (i % 3) * 0.2f),
                    radius = 1f + (i % 3),
                    center = Offset(x, y),
                )
            }
        }

        // Dark overlay gradient at bottom
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0x40000000),
                            Color(0xCC131313),
                        ),
                        startY = 0.4f * 1000f,
                    ),
                ),
        )

        // Badge top-left
        TravelTypeBadge(
            travelType = trip.travelType,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 12.dp),
        )

        // Content overlay at bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            // Vehicle name in yellow
            Text(
                text = trip.vehicleName.uppercase(),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = HankenGrotesk,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    letterSpacing = 1.sp,
                ),
                color = Color(0xFFFFD500),
            )
            // Route title
            Text(
                text = "${trip.origin} — ${trip.destination}",
                modifier = Modifier.semantics { heading() },
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    lineHeight = 44.sp,
                ),
                color = Color(0xFFE5E2E1),
            )
            // Info chips
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                InfoChip(
                    label = stringResource(R.string.detail_status_label),
                    value = stringResource(R.string.detail_status_confirmed),
                    valueColor = Color(0xFFFFD500),
                )
                InfoChip(
                    label = stringResource(R.string.detail_reservation_label),
                    value = "LX-${(trip.id.hashCode() and 0xFFFF).toString().padStart(5, '0')}",
                    valueColor = Color(0xFFE5E2E1),
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun HeroSectionPreview() {
    DSTheme {
        HeroSection(trip = PreviewData.favoriteTrip)
    }
}
