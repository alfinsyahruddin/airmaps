package dev.alfin.airmaps.modules.presentation.pages.detail_location.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.domain.entities.AirQuality
import dev.alfin.airmaps.modules.domain.entities.color
import dev.alfin.airmaps.modules.domain.entities.dummy
import dev.alfin.airmaps.modules.domain.entities.pm25Color
import dev.alfin.airmaps.modules.domain.entities.pm25Status
import dev.alfin.airmaps.modules.presentation.components.CircleText
import dev.alfin.airmaps.modules.presentation.components.MultiStyleText
import dev.alfin.airmaps.modules.presentation.components.TextItem
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@Composable
fun AirQualityBottomSheet(
    modifier: Modifier = Modifier,
    airQuality: AirQuality,
    onClickDownIcon: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircleText(
                text = airQuality.value.toString(),
                color = airQuality.color,
            )

            Column {

                Row {
                    Text(
                        text = airQuality.status,
                        style = MaterialTheme.typography.headlineLarge,
                        color = airQuality.color,
                        modifier = Modifier.weight(1f, fill = true)
                    )

                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = onClickDownIcon
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_down),
                            contentDescription = stringResource(R.string.button_icon),
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F)
                        )
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Dominant Pollutant: ${airQuality.dominantPollutant}",
                    style = MaterialTheme.typography.bodySmall,
                )

                MultiStyleText(
                    TextItem(
                        text = "PM2.5 Status: ",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall
                    ),
                    TextItem(
                        text = "${airQuality.pm25Status} (${airQuality.pm25Value})",
                        color = airQuality.pm25Color,
                        style = MaterialTheme.typography.titleSmall
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Health Recommendation",
                    style = MaterialTheme.typography.titleSmall,
                )

                Text(
                    text = airQuality.healthRecommendation,
                    style = MaterialTheme.typography.bodySmall,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Latitude: ${airQuality.coordinate.latitude}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
                )

                Text(
                    text = "Longitude: ${airQuality.coordinate.longitude}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F)
                )

            }
        }
    }


}

@Preview
@Composable
fun AirQualityBottomSheetPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
        ) {
            AirQualityBottomSheet(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
                airQuality = AirQuality.dummy
            )
        }
    }
}
