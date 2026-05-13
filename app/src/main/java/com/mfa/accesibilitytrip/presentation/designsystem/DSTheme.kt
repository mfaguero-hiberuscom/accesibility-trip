package com.mfa.accesibilitytrip.presentation.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mfa.accesibilitytrip.R

// ── Font Families ──────────────────────────────────────────────────────────────

val Manrope = FontFamily(
    Font(R.font.manrope_w300, FontWeight.Light),
    Font(R.font.manrope_w400, FontWeight.Normal),
    Font(R.font.manrope_w600, FontWeight.SemiBold),
    Font(R.font.manrope_w700, FontWeight.Bold),
    Font(R.font.manrope_w800, FontWeight.ExtraBold),
)

val HankenGrotesk = FontFamily(
    Font(R.font.hanken_grotesk_w400, FontWeight.Normal),
    Font(R.font.hanken_grotesk_w700, FontWeight.Bold),
)

val NicoMoji = FontFamily(
    Font(R.font.nico_moji_regular, FontWeight.Normal),
)

// ── Figma Colors ───────────────────────────────────────────────────────────────

private val Yellow = Color(0xFFFFD500)
private val YellowDark = Color(0xFFE6B800)
private val DarkBg = Color(0xFF0D0D0D)
private val DarkSurface = Color(0xFF242424)
private val DarkNavBar = Color(0xFF151515)
private val DarkCard = Color(0xFF1B1B1C)
private val DarkBoardingPass = Color(0xFF202020)
private val LightBg = Color(0xFFF6F6F6)
private val LightSurface = Color(0xFFFFFFFF)
private val DarkText = Color(0xFF001F2F)
private val DarkSecondaryText = Color(0xFF46474A)
private val LightMutedText = Color(0xFF616161)
private val LightUnselected = Color(0xFF515151)
private val DarkUnselected = Color(0xFFE0E0E0)
private val TealBlue = Color(0xFF005A82)
private val TealBlueLight = Color(0xFF006894)
private val MutedGray = Color(0xFFC4C7C7)
private val CardBorder = Color(0xFF555555)
private val DarkCardBorder = Color(0xFF444748)
private val LightCardBorder = Color(0xFFE0E0E0)
private val CatalogCardBorderColor = Color(0xFF74777F)
private val DarkCatalogCardBg = Color(0xFF252525)

private val LightColors = lightColorScheme(
    primary = Yellow,
    onPrimary = Color.Black,
    secondary = TealBlue,
    onSecondary = Color.White,
    tertiary = TealBlueLight,
    onTertiary = Color.White,
    background = LightBg,
    onBackground = DarkText,
    surface = LightSurface,
    onSurface = DarkText,
    surfaceVariant = LightBg,
    onSurfaceVariant = DarkSecondaryText,
    outline = LightCardBorder,
    outlineVariant = Color(0xFFCDCDCD),
    inverseSurface = Color(0xFF3A3A3A),
    inverseOnSurface = Color.White,
)

private val DarkColors = darkColorScheme(
    primary = Yellow,
    onPrimary = Color.Black,
    secondary = TealBlue,
    onSecondary = Color.White,
    tertiary = YellowDark,
    onTertiary = Color.Black,
    background = DarkBg,
    onBackground = Color.White,
    surface = DarkSurface,
    onSurface = Color.White,
    surfaceVariant = DarkNavBar,
    onSurfaceVariant = DarkUnselected,
    outline = CardBorder,
    outlineVariant = CardBorder,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
)

// ── Extended Colors (not in Material3 scheme) ──────────────────────────────────

@Immutable
data class DSExtendedColors(
    val priceColor: Color,
    val subtitleAccent: Color,
    val mutedText: Color,
    val cardBorder: Color,
    val detailCardBg: Color,
    val detailCardBorder: Color,
    val boardingPassBg: Color,
    val navBarBg: Color,
    val unselectedNav: Color,
    val badgeNormalBg: Color,
    val badgeNormalText: Color,
    val catalogCardBg: Color,
    val catalogCardBorder: Color,
    val navBarBorder: Color,
)

val LocalDSExtendedColors = staticCompositionLocalOf {
    DSExtendedColors(
        priceColor = Yellow,
        subtitleAccent = Yellow,
        mutedText = MutedGray,
        cardBorder = CardBorder,
        detailCardBg = DarkCard,
        detailCardBorder = DarkCardBorder,
        boardingPassBg = DarkBoardingPass,
        navBarBg = DarkNavBar,
        unselectedNav = DarkUnselected,
        badgeNormalBg = Color.White,
        badgeNormalText = Color.Black,
        catalogCardBg = DarkCatalogCardBg,
        catalogCardBorder = CatalogCardBorderColor,
        navBarBorder = CardBorder,
    )
}

private val LightExtendedColors = DSExtendedColors(
    priceColor = TealBlue,
    subtitleAccent = TealBlueLight,
    mutedText = LightMutedText,
    cardBorder = LightCardBorder,
    detailCardBg = LightSurface,
    detailCardBorder = LightCardBorder,
    boardingPassBg = LightSurface,
    navBarBg = LightSurface,
    unselectedNav = LightUnselected,
    badgeNormalBg = Color(0xFF3A3A3A),
    badgeNormalText = Color.White,
    catalogCardBg = LightSurface,
    catalogCardBorder = LightCardBorder,
    navBarBorder = Color(0xFFE8E8E8),
)

private val DarkExtendedColors = DSExtendedColors(
    priceColor = Yellow,
    subtitleAccent = Yellow,
    mutedText = MutedGray,
    cardBorder = CardBorder,
    detailCardBg = DarkCard,
    detailCardBorder = DarkCardBorder,
    boardingPassBg = DarkBoardingPass,
    navBarBg = DarkNavBar,
    unselectedNav = DarkUnselected,
    badgeNormalBg = Color.White,
    badgeNormalText = Color.Black,
    catalogCardBg = DarkCatalogCardBg,
    catalogCardBorder = CatalogCardBorderColor,
    navBarBorder = CardBorder,
)

// ── Typography ─────────────────────────────────────────────────────────────────

private val AppTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 44.sp,
        lineHeight = 55.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = HankenGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
)

// ── Theme ──────────────────────────────────────────────────────────────────────

object DSThemeDefaults {
    val extendedColors: DSExtendedColors
        @Composable get() = LocalDSExtendedColors.current
}

@Composable
fun DSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalDSExtendedColors provides extendedColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content,
        )
    }
}
