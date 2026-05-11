package com.mfa.accesibilitytrip.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel

class TripsViewModel : ViewModel() {

    private var allTrips by mutableStateOf(TripCardUiModel.mockCatalog())
        private set

    val favoriteTrips: List<TripCardUiModel>
        get() = allTrips.filter { trip -> trip.isFavorite }

    val catalogTrips: List<TripCardUiModel>
        get() = allTrips

    fun removeFavorite(tripId: String) {
        updateFavorite(tripId = tripId, isFavorite = false)
    }

    fun addFavorite(tripId: String) {
        updateFavorite(tripId = tripId, isFavorite = true)
    }

    fun toggleFavorite(tripId: String) {
        allTrips = allTrips.map { trip ->
            if (trip.id == tripId) {
                trip.copy(isFavorite = !trip.isFavorite)
            } else {
                trip
            }
        }
    }

    fun findTrip(tripId: String): TripCardUiModel? {
        return allTrips.firstOrNull { trip -> trip.id == tripId }
    }

    private fun updateFavorite(
        tripId: String,
        isFavorite: Boolean,
    ) {
        allTrips = allTrips.map { trip ->
            if (trip.id == tripId) {
                trip.copy(isFavorite = isFavorite)
            } else {
                trip
            }
        }
    }
}
