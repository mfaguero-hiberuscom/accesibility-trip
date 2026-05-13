package com.mfa.accesibilitytrip.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mfa.accesibilitytrip.presentation.model.SettingsToggleUiModel

class SettingsViewModel : ViewModel() {

    var isDarkMode by mutableStateOf<Boolean?>(null)
        private set

    var toggles by mutableStateOf(SettingsToggleUiModel.mockList())
        private set

    fun setDarkMode(enabled: Boolean) {
        isDarkMode = enabled
    }

    fun toggleDarkMode() {
        isDarkMode = !(isDarkMode ?: false)
    }

    fun toggle(toggleId: String) {
        if (toggleId == "dark-mode") {
            toggleDarkMode()
            toggles = toggles.map { toggle ->
                if (toggle.id == "dark-mode") {
                    toggle.copy(enabled = isDarkMode ?: false)
                } else {
                    toggle
                }
            }
            return
        }
        toggles = toggles.map { toggle ->
            if (toggle.id == toggleId) {
                toggle.copy(enabled = !toggle.enabled)
            } else {
                toggle
            }
        }
    }
}
