package com.mfa.accesibilitytrip.presentation.screen.commoncomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.model.TravelTypeUiModel

@Composable
internal fun TravelTypeBadge(
    travelType: TravelTypeUiModel,
    modifier: Modifier = Modifier,
) {
    val extendedColors = DSThemeDefaults.extendedColors
    val isHyper = travelType == TravelTypeUiModel.HYPERLUMINOUS
    val containerColor = if (isHyper) {
        MaterialTheme.colorScheme.primary
    } else {
        extendedColors.badgeNormalBg
    }
    val textColor = if (isHyper) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        extendedColors.badgeNormalText
    }

    Surface(
        modifier = modifier,
        color = containerColor,
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = stringResource(travelType.labelRes),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = textColor,
        )
    }
}