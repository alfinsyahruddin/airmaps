package dev.alfin.airmaps.modules.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import dev.alfin.airmaps.modules.presentation.theme.backgroundGradientEnd
import dev.alfin.airmaps.modules.presentation.theme.backgroundGradientStart

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    start: Color = MaterialTheme.colorScheme.backgroundGradientStart,
    end: Color = MaterialTheme.colorScheme.backgroundGradientEnd,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        start,
                        end,
                    )
                )
            )
    ) {
        content()
    }
}