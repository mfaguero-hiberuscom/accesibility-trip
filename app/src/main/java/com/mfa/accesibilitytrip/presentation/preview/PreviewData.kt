package com.mfa.accesibilitytrip.presentation.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import com.mfa.accesibilitytrip.presentation.model.SettingsToggleUiModel
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel

@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFF6F6F6,
)
@Preview(
    name = "Dark",
    showBackground = true,
    backgroundColor = 0xFF0D0D0D,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class ThemePreviews

internal object PreviewData {
    private val trips by lazy(LazyThreadSafetyMode.NONE) {
        TripCardUiModel.mockCatalog()
    }
    private val toggles by lazy(LazyThreadSafetyMode.NONE) {
        SettingsToggleUiModel.mockList()
    }

    val favoriteTrip: TripCardUiModel
        get() = trips.first { trip -> trip.isFavorite }

    val regularTrip: TripCardUiModel
        get() = trips.first { trip -> !trip.isFavorite }

    val favoriteTrips: List<TripCardUiModel>
        get() = trips.filter { trip -> trip.isFavorite }

    val catalogTrips: List<TripCardUiModel>
        get() = trips.take(8)

    val enabledToggle: SettingsToggleUiModel
        get() = toggles.first { toggle -> toggle.enabled }

    val disabledToggle: SettingsToggleUiModel
        get() = toggles.first { toggle -> !toggle.enabled }

    val settingsToggles: List<SettingsToggleUiModel>
        get() = toggles
}
