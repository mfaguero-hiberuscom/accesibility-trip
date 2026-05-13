package com.mfa.accesibilitytrip.presentation.screen.catalogscreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.preview.PreviewData
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews

@Composable
internal fun PlanetImagePlaceholder(
    planetName: String,
    modifier: Modifier = Modifier,
) {
    val colors = remember(planetName) {
        val hash = planetName.hashCode()
        val hue1 = ((hash and 0xFF) / 255f * 360f)
        val hue2 = (((hash shr 8) and 0xFF) / 255f * 360f)
        listOf(
            Color.hsl(hue1, 0.5f, 0.3f),
            Color.hsl(hue2, 0.6f, 0.5f),
            Color.hsl((hue1 + 40f) % 360f, 0.4f, 0.2f),
        )
    }
    val primary = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier) {
        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset.Zero,
                end = Offset(size.width, size.height),
            ),
        )
        drawCircle(
            color = colors[1].copy(alpha = 0.6f),
            radius = size.minDimension * 0.3f,
            center = Offset(size.width * 0.6f, size.height * 0.4f),
        )
        drawCircle(
            color = primary.copy(alpha = 0.2f),
            radius = size.minDimension * 0.35f,
            center = Offset(size.width * 0.6f, size.height * 0.4f),
        )
    }
}

@ThemePreviews
@Composable
private fun PlanetImagePlaceholderPreview() {
    DSTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PlanetImagePlaceholder(
                planetName = PreviewData.favoriteTrip.destination,
                modifier = Modifier
                    .size(112.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
            PlanetImagePlaceholder(
                planetName = PreviewData.regularTrip.destination,
                modifier = Modifier
                    .size(112.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        }
    }
}
