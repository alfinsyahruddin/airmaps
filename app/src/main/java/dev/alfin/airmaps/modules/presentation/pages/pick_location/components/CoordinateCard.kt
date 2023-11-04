package dev.alfin.airmaps.modules.presentation.pages.pick_location.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import dev.alfin.airmaps.modules.domain.entities.dummy
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@Composable
fun CoordinateCard(
    modifier: Modifier = Modifier,
    coordinate: Coordinate
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .semantics(mergeDescendants = true) {}
        ) {

            Text(
                text = "Latitude: ${coordinate.latitude}",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "Longitude: ${coordinate.longitude}",
                style = MaterialTheme.typography.bodyMedium,
            )

        }
    }
}

@Preview
@Composable
fun CoordinateCardPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
        ) {
            CoordinateCard(
                modifier = Modifier
                    .padding(16.dp),
                coordinate = Coordinate.dummy
            )
        }
    }
}