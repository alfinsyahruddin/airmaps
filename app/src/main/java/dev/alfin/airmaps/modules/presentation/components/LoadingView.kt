package dev.alfin.airmaps.modules.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    size: Int = 60,
    padding: Int = 32
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Black.copy(alpha = 0.25f),
            border = BorderStroke(1.dp, Color.White.copy(0.25f)),
            shadowElevation = 4.dp,
        ) {}

        Surface(
            modifier = Modifier.size((size + padding * 2).dp),
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.surface,
        ) {}

        CircularProgressIndicator(
            modifier = Modifier.size(size.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 6.dp
        )
    }
}

@Preview
@Composable
fun LoadingViewPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Page Content")
            }

            LoadingView()
        }
    }
}