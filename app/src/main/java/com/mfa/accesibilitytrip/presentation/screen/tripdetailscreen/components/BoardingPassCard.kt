package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.designsystem.Manrope
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews

@Composable
internal fun BoardingPassCard() {
    val extendedColors = DSThemeDefaults.extendedColors

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.detail_boarding_pass),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )

            // QR Code placeholder
            Surface(
                modifier = Modifier
                    .size(112.dp)
                    .semantics {
                        contentDescription = "Código QR de la tarjeta de embarque"
                    },
                shape = RoundedCornerShape(4.dp),
                color = Color.White,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val cellSize = size.width / 10
                        for (row in 0 until 10) {
                            for (col in 0 until 10) {
                                if ((row + col) % 2 == 0 || (row * col) % 3 == 0) {
                                    drawRect(
                                        color = Color.Black,
                                        topLeft = Offset(col * cellSize, row * cellSize),
                                        size = Size(
                                            cellSize,
                                            cellSize,
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Text(
                text = stringResource(R.string.detail_boarding_scan),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                ),
                color = extendedColors.mutedText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@ThemePreviews
@Composable
private fun BoardingPassCardPreview() {
    DSTheme {
        BoardingPassCard()
    }
}
