package com.mfa.accesibilitytrip.presentation.screen.settingsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.model.SettingsToggleUiModel
import com.mfa.accesibilitytrip.presentation.screen.settingsscreen.components.SettingRow
import com.mfa.accesibilitytrip.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
) {
    SettingsContent(
        toggles = settingsViewModel.toggles,
        onToggle = settingsViewModel::toggle,
        modifier = modifier,
    )
}

@Composable
private fun SettingsContent(
    toggles: List<SettingsToggleUiModel>,
    onToggle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.settings_title),
            modifier = Modifier.semantics { heading() },
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.settings_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
        ) {
            items(
                items = toggles,
                key = { toggle -> toggle.id },
            ) { toggle ->
                SettingRow(
                    toggle = toggle,
                    onToggle = { onToggle(toggle.id) },
                )
            }
        }
        Text(
            text = stringResource(R.string.settings_footer),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}



@Preview(showBackground = true)
@Composable
private fun SettingsContentPreview() {
    DSTheme {
        SettingsContent(
            toggles = SettingsViewModel().toggles,
            onToggle = {},
        )
    }
}
