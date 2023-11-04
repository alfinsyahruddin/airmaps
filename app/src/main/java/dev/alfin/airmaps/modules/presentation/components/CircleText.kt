package dev.alfin.airmaps.modules.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@Composable
fun CircleText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    size: Int = 40,
    borderWidth: Int = 16
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size((size + borderWidth).dp)
            .border(
                width = borderWidth.dp,
                color = color.copy(alpha = 0.25F),
                shape = MaterialTheme.shapes.extraLarge
            ),
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(color)
        )

        Text(
            text = "$text",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Preview
@Composable
fun CircleTextPreview() {
    AppTheme {
        CircleText(
            text = "10",
            color = MaterialTheme.colorScheme.primary
        )
    }
}