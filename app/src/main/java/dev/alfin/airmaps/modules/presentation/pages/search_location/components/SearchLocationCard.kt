package dev.alfin.airmaps.modules.presentation.pages.search_location.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.entities.dummy
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationCard(
    modifier: Modifier = Modifier,
    location: Location,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f)),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {

            Image(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Filled.LocationOn,
                contentDescription = stringResource(R.string.button_icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = location.description,
                    style = MaterialTheme.typography.bodyMedium,
                )

            }

        }
    }
}

@Preview
@Composable
fun SearchLocationCardPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
        ) {
            SearchLocationCard(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                location = Location.dummy
            )
        }
    }
}