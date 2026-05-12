package com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults


@Composable
internal fun HeaderBlock(favoriteCount: Int) {
    Column {
        Text(
            text = stringResource(R.string.home_title),
            modifier = Modifier
                .semantics { heading() }
                .padding(bottom = 8.dp),
            style = MaterialTheme.typography.displaySmall,
        )
        Text(
            text = stringResource(R.string.home_routes_count, favoriteCount),
            style = MaterialTheme.typography.labelLarge,
            color = DSThemeDefaults.extendedColors.subtitleAccent,
        )
    }
}
