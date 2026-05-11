package com.mfa.accesibilitytrip.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.navigation.AppRoute
import com.mfa.accesibilitytrip.presentation.navigation.CatalogRoute
import com.mfa.accesibilitytrip.presentation.navigation.FavoriteTripsRoute
import com.mfa.accesibilitytrip.presentation.navigation.SettingsRoute
import com.mfa.accesibilitytrip.presentation.navigation.TripDetailRoute
import com.mfa.accesibilitytrip.presentation.screen.CatalogScreen
import com.mfa.accesibilitytrip.presentation.screen.FavoriteTripsScreen
import com.mfa.accesibilitytrip.presentation.screen.SettingsScreen
import com.mfa.accesibilitytrip.presentation.screen.TripDetailScreen
import com.mfa.accesibilitytrip.presentation.viewmodel.SettingsViewModel
import com.mfa.accesibilitytrip.presentation.viewmodel.TripsViewModel

@Composable
fun AccesibilityTripApp(
    tripsViewModel: TripsViewModel,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
) {
    val backStack = remember { mutableStateListOf<AppRoute>(FavoriteTripsRoute) }
    val currentRoute = backStack.lastOrNull() ?: FavoriteTripsRoute
    val selectedDestination = currentRoute.toTopLevelDestination()

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavigationBar(
                selectedDestination = selectedDestination,
                onDestinationSelected = { destination ->
                    if (selectedDestination != destination || backStack.size > 1) {
                        backStack.clear()
                        backStack.add(destination.route)
                    }
                },
            )
        },
    ) { innerPadding ->
        AppNavDisplay(
            innerPadding = innerPadding,
            tripsViewModel = tripsViewModel,
            settingsViewModel = settingsViewModel,
            onBack = { backStack.removeLastOrNull() },
            onOpenTrip = { tripId -> backStack.add(TripDetailRoute(tripId)) },
            onOpenCatalog = { backStack.add(CatalogRoute) },
            backStack = backStack,
        )
    }
}

@Composable
private fun AppNavDisplay(
    innerPadding: PaddingValues,
    tripsViewModel: TripsViewModel,
    settingsViewModel: SettingsViewModel,
    onBack: () -> Unit,
    onOpenTrip: (String) -> Unit,
    onOpenCatalog: () -> Unit,
    backStack: MutableList<AppRoute>,
) {
    NavDisplay(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        backStack = backStack,
        onBack = onBack,
        entryProvider = entryProvider {
            entry<FavoriteTripsRoute> {
                FavoriteTripsScreen(
                    tripsViewModel = tripsViewModel,
                    onOpenTrip = onOpenTrip,
                    onOpenCatalog = onOpenCatalog,
                )
            }
            entry<CatalogRoute> {
                CatalogScreen(
                    tripsViewModel = tripsViewModel,
                    onBack = onBack,
                    onOpenTrip = onOpenTrip,
                )
            }
            entry<TripDetailRoute> { route ->
                TripDetailScreen(
                    tripsViewModel = tripsViewModel,
                    tripId = route.tripId,
                    onBack = onBack,
                )
            }
            entry<SettingsRoute> {
                SettingsScreen(
                    settingsViewModel = settingsViewModel,
                )
            }
        },
    )
}

@Composable
private fun BottomNavigationBar(
    selectedDestination: TopLevelDestination,
    onDestinationSelected: (TopLevelDestination) -> Unit,
) {
    NavigationBar {
        TopLevelDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = destination == selectedDestination,
                onClick = { onDestinationSelected(destination) },
                icon = {
                    Icon(
                        painter = painterResource(destination.iconRes),
                        contentDescription = null,
                    )
                },
                label = { Text(stringResource(destination.labelRes)) },
            )
        }
    }
}

private enum class TopLevelDestination(
    @get:StringRes val labelRes: Int,
    @get:DrawableRes val iconRes: Int,
    val route: AppRoute,
) {
    FAVORITES(
        labelRes = R.string.tab_favorite_trips,
        iconRes = R.drawable.ic_home,
        route = FavoriteTripsRoute,
    ),
    SETTINGS(
        labelRes = R.string.tab_settings,
        iconRes = android.R.drawable.ic_menu_manage,
        route = SettingsRoute,
    ),
}

private fun AppRoute.toTopLevelDestination(): TopLevelDestination {
    return when (this) {
        is SettingsRoute -> TopLevelDestination.SETTINGS
        else -> TopLevelDestination.FAVORITES
    }
}

@Preview
@Composable
private fun AccesibilityTripAppPreview() {
    com.mfa.accesibilitytrip.presentation.designsystem.DSTheme {
        AccesibilityTripApp(
            tripsViewModel = TripsViewModel(),
            settingsViewModel = SettingsViewModel(),
        )
    }
}
