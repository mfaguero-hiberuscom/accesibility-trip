package com.mfa.accesibilitytrip.presentation

import com.mfa.accesibilitytrip.presentation.viewmodel.SettingsViewModel
import com.mfa.accesibilitytrip.presentation.viewmodel.TripsViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PresentationViewModelTest {

    @Test
    fun removeFavorite_removesOnlyRequestedTrip() {
        val viewModel = TripsViewModel()
        val tripId = viewModel.favoriteTrips.first().id
        val initialSize = viewModel.favoriteTrips.size

        viewModel.removeFavorite(tripId)

        assertEquals(initialSize - 1, viewModel.favoriteTrips.size)
        assertFalse(viewModel.favoriteTrips.any { trip -> trip.id == tripId })
        assertNotNull(viewModel.findTrip(tripId))
        assertFalse(viewModel.findTrip(tripId)?.isFavorite == true)
    }

    @Test
    fun catalog_startsWithOneHundredTripsAndThreeFavorites() {
        val viewModel = TripsViewModel()

        assertEquals(100, viewModel.catalogTrips.size)
        assertEquals(3, viewModel.favoriteTrips.size)
    }

    @Test
    fun toggleFavorite_addsTripToFavoritesFromCatalog() {
        val viewModel = TripsViewModel()
        val nonFavoriteTrip = viewModel.catalogTrips.first { trip -> !trip.isFavorite }

        viewModel.toggleFavorite(nonFavoriteTrip.id)

        assertTrue(viewModel.favoriteTrips.any { trip -> trip.id == nonFavoriteTrip.id })
        assertTrue(viewModel.findTrip(nonFavoriteTrip.id)?.isFavorite == true)
    }

    @Test
    fun toggle_changesOnlyTargetSetting() {
        val viewModel = SettingsViewModel()
        val initialFirst = viewModel.toggles.first()
        val initialSecond = viewModel.toggles[1]

        viewModel.toggle(initialFirst.id)

        assertNotEquals(initialFirst.enabled, viewModel.toggles.first().enabled)
        assertEquals(initialSecond.enabled, viewModel.toggles[1].enabled)
    }
}
