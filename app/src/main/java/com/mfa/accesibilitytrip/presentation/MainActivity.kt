package com.mfa.accesibilitytrip.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.viewmodel.SettingsViewModel
import com.mfa.accesibilitytrip.presentation.viewmodel.TripsViewModel

class MainActivity : ComponentActivity() {

    private val tripsViewModel by viewModels<TripsViewModel>()
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()
            val isDark = settingsViewModel.isDarkMode ?: systemDark

            DSTheme(darkTheme = isDark) {
                AccesibilityTripApp(
                    tripsViewModel = tripsViewModel,
                    settingsViewModel = settingsViewModel,
                )
            }
        }
    }
}
