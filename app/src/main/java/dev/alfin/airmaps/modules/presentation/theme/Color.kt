package dev.alfin.airmaps.modules.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// MARK: - Light
val md_theme_light_primary = Color(0xFF56C2FF)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFF56C2FF)
val md_theme_light_onPrimaryContainer = Color(0xFFFFFFFF)
val md_theme_light_secondary = Color(0xFF725C00)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFFFE080)
val md_theme_light_onSecondaryContainer = Color(0xFF231B00)
val md_theme_light_tertiary = Color(0xFF56C2FF)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFF56C2FF)
val md_theme_light_onTertiaryContainer = Color(0xFFFFFFFF)
val md_theme_light_error = Color(0xFFF6676B)
val md_theme_light_errorContainer = Color(0xFFF6676B)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFFFF)
val md_theme_light_onBackground = Color(0xFF000000)
val md_theme_light_surface = Color(0xFFFFFFFF)
val md_theme_light_onSurface = Color(0xFF000000)
val md_theme_light_surfaceVariant = Color(0xFFDDE3EA)
val md_theme_light_onSurfaceVariant = Color(0xFF41484D)
val md_theme_light_outline = Color(0xFF71787E)
val md_theme_light_inverseOnSurface = Color(0xFFFFFFFF)
val md_theme_light_inverseSurface = Color(0xFF00363F)
val md_theme_light_inversePrimary = Color(0xFF56C2FF)
val md_theme_light_surfaceTint = Color(0xFFFFFFFF)
val md_theme_light_outlineVariant = Color(0xFFC1C7CE)
val md_theme_light_scrim = Color(0xFF000000)


// MARK: - Dark
val md_theme_dark_primary = Color(0xFF56C2FF)
val md_theme_dark_onPrimary = Color(0xFFFFFFFF)
val md_theme_dark_primaryContainer = Color(0xFF56C2FF)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFFFFF)
val md_theme_dark_secondary = Color(0xFFE7C349)
val md_theme_dark_onSecondary = Color(0xFF3C2F00)
val md_theme_dark_secondaryContainer = Color(0xFF564500)
val md_theme_dark_onSecondaryContainer = Color(0xFFFFE080)
val md_theme_dark_tertiary = Color(0xFF56C2FF)
val md_theme_dark_onTertiary = Color(0xFFFFFFFF)
val md_theme_dark_tertiaryContainer = Color(0xFF56C2FF)
val md_theme_dark_onTertiaryContainer = Color(0xFF56C2FF)
val md_theme_dark_error = Color(0xFFF6676B)
val md_theme_dark_errorContainer = Color(0xFFF6676B)
val md_theme_dark_onError = Color(0xFFFFFFFF)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF152041)
val md_theme_dark_onBackground = Color(0xFFFFFFFF)
val md_theme_dark_surface = Color(0xFF152041)
val md_theme_dark_onSurface = Color(0xFFFFFFFF)
val md_theme_dark_surfaceVariant = Color(0xFF41484D)
val md_theme_dark_onSurfaceVariant = Color(0xFFC1C7CE)
val md_theme_dark_outline = Color(0xFF8B9198)
val md_theme_dark_inverseOnSurface = Color(0xFF001F25)
val md_theme_dark_inverseSurface = Color(0xFFA6EEFF)
val md_theme_dark_inversePrimary = Color(0xFF56C2FF)
val md_theme_dark_surfaceTint = Color(0xFF152041)
val md_theme_dark_outlineVariant = Color(0xFF41484D)
val md_theme_dark_scrim = Color(0xFF000000)

// MARK: - Color Scheme
val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)

// MARK: - Custom Colors
val ColorScheme.backgroundGradientStart: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF0E1936) else Color(0xFFEAF5FF)

val ColorScheme.backgroundGradientEnd: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF090E20) else Color(0xFFFFFFFF)

val ColorScheme.surfaceGradientStart: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF152041) else Color(0xFFFFFFFF)

val ColorScheme.surfaceGradientEnd: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF152041) else Color(0xFFF4FAFF)

val ColorScheme.disabledContainer: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF222D51) else Color(0xFFA5DEFF)

val ColorScheme.onDisabledContainer: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF566490) else Color(0xFFFFFFFF)
