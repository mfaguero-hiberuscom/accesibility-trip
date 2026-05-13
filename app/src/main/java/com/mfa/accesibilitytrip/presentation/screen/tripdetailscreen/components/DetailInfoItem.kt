package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.designsystem.HankenGrotesk
import com.mfa.accesibilitytrip.presentation.designsystem.Manrope

@Composable
internal fun DetailInfoItem(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = HankenGrotesk,
                fontWeight = FontWeight.Normal,
            ),
            color = DSThemeDefaults.extendedColors.mutedText,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
            ),
            color = valueColor,
        )
    }
}
