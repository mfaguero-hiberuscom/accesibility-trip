package com.mfa.accesibilitytrip.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mfa.accesibilitytrip.presentation.model.SettingsToggleUiModel

class SettingsViewModel : ViewModel() {

    var toggles by mutableStateOf(SettingsToggleUiModel.mockList())
        private set

    fun toggle(toggleId: String) {
        toggles = toggles.map { toggle ->
            if (toggle.id == toggleId) {
                toggle.copy(enabled = !toggle.enabled)
            } else {
                toggle
            }
        }
    }
}
