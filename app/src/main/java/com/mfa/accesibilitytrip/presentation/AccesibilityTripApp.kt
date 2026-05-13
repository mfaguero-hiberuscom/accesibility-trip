package com.mfa.accesibilitytrip.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.designsystem.DSTheme
import com.mfa.accesibilitytrip.presentation.designsystem.DSThemeDefaults
import com.mfa.accesibilitytrip.presentation.designsystem.NicoMoji
import com.mfa.accesibilitytrip.presentation.navigation.AppRoute
import com.mfa.accesibilitytrip.presentation.navigation.CatalogRoute
import com.mfa.accesibilitytrip.presentation.navigation.FavoriteTripsRoute
import com.mfa.accesibilitytrip.presentation.navigation.SettingsRoute
import com.mfa.accesibilitytrip.presentation.navigation.TripDetailRoute
import com.mfa.accesibilitytrip.presentation.preview.ThemePreviews
import com.mfa.accesibilitytrip.presentation.screen.catalogscreen.CatalogScreen
import com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.FavoriteTripsScreen
import com.mfa.accesibilitytrip.presentation.screen.settingsscreen.SettingsScreen
import com.mfa.accesibilitytrip.presentation.screen.tripdetailscreen.TripDetailScreen
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
    val isDetailScreen = currentRoute is TripDetailRoute
    val isCatalogScreen = currentRoute is CatalogRoute
    val hideChrome = isDetailScreen || isCatalogScreen

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            if (!hideChrome) {
                PluJourneyTopBar()
            }
        },
        bottomBar = {
            if (!hideChrome) {
                PluJourneyBottomBar(
                    selectedDestination = selectedDestination,
                    onDestinationSelected = { destination ->
                        if (selectedDestination != destination || backStack.size > 1) {
                            backStack.clear()
                            backStack.add(destination.route)
                        }
                    },
                )
            }
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
private fun PluJourneyTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
        val logoRes = if (isDark) {
            R.drawable.ic_logo_dark
        } else {
            R.drawable.ic_logo_light
        }
        Image(
            painter = painterResource(logoRes),
            contentDescription = "PluJourney logo",
            modifier = Modifier.size(50.dp),
        )
        Text(
            text = stringResource(R.string.logo_name),
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = NicoMoji,
                fontSize = 24.sp,
            ),
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun PluJourneyBottomBar(
    selectedDestination: TopLevelDestination,
    onDestinationSelected: (TopLevelDestination) -> Unit,
) {
    val extendedColors = DSThemeDefaults.extendedColors

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = extendedColors.navBarBg,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TopLevelDestination.entries.forEach { destination ->
                val isSelected = destination == selectedDestination
                val iconTint = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    extendedColors.unselectedNav
                }
                val labelColor = if (isSelected) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    extendedColors.unselectedNav
                }

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onDestinationSelected(destination) }
                        .semantics { role = Role.Tab }
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (isSelected) MaterialTheme.colorScheme.primary
                                else androidx.compose.ui.graphics.Color.Transparent,
                            )
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            painter = painterResource(destination.iconRes),
                            contentDescription = null,
                            modifier = Modifier.size(22.dp),
                            tint = iconTint,
                        )
                    }
                    Text(
                        text = stringResource(destination.labelRes),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) {
                                androidx.compose.ui.text.font.FontWeight.Bold
                            } else {
                                androidx.compose.ui.text.font.FontWeight.Normal
                            },
                        ),
                        color = labelColor,
                    )
                }
            }
        }
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
        iconRes = R.drawable.ic_settings,
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
    DSTheme {
        AccesibilityTripApp(
            tripsViewModel = TripsViewModel(),
            settingsViewModel = SettingsViewModel(),
        )
    }
}

@ThemePreviews
@Composable
private fun PluJourneyTopBarPreview() {
    DSTheme {
        PluJourneyTopBar()
    }
}

@ThemePreviews
@Composable
private fun PluJourneyBottomBarPreview() {
    DSTheme {
        Column {
            PluJourneyBottomBar(
                selectedDestination = TopLevelDestination.FAVORITES,
                onDestinationSelected = {},
            )
            Spacer(modifier = Modifier.height(16.dp))
            PluJourneyBottomBar(
                selectedDestination = TopLevelDestination.SETTINGS,
                onDestinationSelected = {},
            )
        }
    }
}
