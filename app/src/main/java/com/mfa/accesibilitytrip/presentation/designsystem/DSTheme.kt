package com.mfa.accesibilitytrip.presentation.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val SpaceBlue = Color(0xFF10203B)
private val SpaceBlueDark = Color(0xFF091427)
private val SpaceCream = Color(0xFFF7F4EC)
private val SpaceCard = Color(0xFFFDFBF7)
private val InkBlue = Color(0xFF16243F)
private val MutedInk = Color(0xFF57637B)
private val PlanetAqua = Color(0xFF73D9FF)
private val RingGold = Color(0xFFF4C95D)
private val SolarCoral = Color(0xFFF07E6E)
private val SurfaceMist = Color(0xFFE6EEF7)
private val SoftWhite = Color(0xFFF7FAFF)

private val LightColors = lightColorScheme(
    primary = SolarCoral,
    onPrimary = SpaceBlueDark,
    secondary = PlanetAqua,
    onSecondary = SpaceBlueDark,
    tertiary = RingGold,
    onTertiary = SpaceBlueDark,
    background = SpaceCream,
    onBackground = InkBlue,
    surface = SpaceCard,
    onSurface = InkBlue,
    surfaceVariant = SurfaceMist,
    onSurfaceVariant = MutedInk,
    outline = MutedInk.copy(alpha = 0.45f),
)

private val DarkColors = darkColorScheme(
    primary = RingGold,
    onPrimary = SpaceBlueDark,
    secondary = PlanetAqua,
    onSecondary = SpaceBlueDark,
    tertiary = SolarCoral,
    onTertiary = SpaceBlueDark,
    background = SpaceBlueDark,
    onBackground = SoftWhite,
    surface = SpaceBlue,
    onSurface = SoftWhite,
    surfaceVariant = SpaceBlue.copy(alpha = 0.82f),
    onSurfaceVariant = SoftWhite.copy(alpha = 0.78f),
    outline = SoftWhite.copy(alpha = 0.24f),
)

private val AccessibilityTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
)

@Composable
fun DSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AccessibilityTypography,
        content = content,
    )
}
