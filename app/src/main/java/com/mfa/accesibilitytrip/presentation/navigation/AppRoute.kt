package com.mfa.accesibilitytrip.presentation.navigation

sealed interface AppRoute

data object FavoriteTripsRoute : AppRoute

data object CatalogRoute : AppRoute

data object SettingsRoute : AppRoute

data class TripDetailRoute(val tripId: String) : AppRoute
