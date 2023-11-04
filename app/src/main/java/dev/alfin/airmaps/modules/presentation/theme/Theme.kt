package dev.alfin.airmaps.modules.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView


@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    val window = (LocalView.current.context as? Activity)?.window

    SideEffect {
        if (window != null) {
            window.statusBarColor = colors.primary.toArgb()
            window.navigationBarColor = (
                    if (useDarkTheme)
                        Color(0xFF090E20)
                    else
                        Color(0xFFFFFFFF)
                    ).toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}