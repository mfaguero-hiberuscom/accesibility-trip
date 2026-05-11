package com.mfa.accesibilitytrip.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.viewmodel.SettingsViewModel
import com.mfa.accesibilitytrip.presentation.viewmodel.TripsViewModel

class MainActivity : ComponentActivity() {

    private val tripsViewModel by viewModels<TripsViewModel>()
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DSTheme {
                AccesibilityTripApp(
                    tripsViewModel = tripsViewModel,
                    settingsViewModel = settingsViewModel,
                )
            }
        }
    }
}
