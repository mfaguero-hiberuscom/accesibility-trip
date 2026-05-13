package com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mfa.accesibilitytrip.presentation.designsystem.HankenGrotesk
import com.mfa.accesibilitytrip.presentation.designsystem.Manrope

@Composable
internal fun InfoChip(
    label: String,
    value: String,
    valueColor: Color,
) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        color = Color(0xFF1E1E1E).copy(alpha = 0.7f),
        border = BorderStroke(1.dp, Color(0xFF8E9192)),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = HankenGrotesk,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                ),
                color = Color(0xFFC4C7C7),
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                ),
                color = valueColor,
            )
        }
    }
}
