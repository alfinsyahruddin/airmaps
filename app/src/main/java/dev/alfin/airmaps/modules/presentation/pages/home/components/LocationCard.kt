package dev.alfin.airmaps.modules.presentation.pages.home.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.entities.dummy
import dev.alfin.airmaps.modules.presentation.components.GradientBackground
import dev.alfin.airmaps.modules.presentation.theme.AppTheme
import dev.alfin.airmaps.modules.presentation.theme.backgroundGradientEnd
import dev.alfin.airmaps.modules.presentation.theme.surfaceGradientEnd
import dev.alfin.airmaps.modules.presentation.theme.surfaceGradientStart

@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    location: Location,
    onClick: () -> Unit = {},
    onClickEdit: () -> Unit = {},
    onClickDelete: () -> Unit = {}
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Box(Modifier.clickable(onClick = onClick)) {
            GradientBackground(
                start = MaterialTheme.colorScheme.surfaceGradientStart,
                end = MaterialTheme.colorScheme.surfaceGradientEnd,
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = stringResource(R.string.button_icon),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error)
                        )

                        Text(
                            text = location.name,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = true)
                        )


                        Box(
                            modifier = Modifier
                                .wrapContentSize(Alignment.TopStart)
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .size(24.dp)
                                    .testTag("more-button"),
                                onClick = { isExpanded = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.MoreVert,
                                    contentDescription = stringResource(R.string.button_icon),
                                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F)
                                )
                            }

                            DropdownMenu(
                                modifier = Modifier.border(1.dp, Color.White.copy(alpha = 0.1f)),
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false },
                            ) {
                                DropdownMenuItem(
                                    modifier = Modifier.testTag("edit-button"),
                                    onClick = onClickEdit,
                                    text = {
                                        Text(
                                            text = "Edit",
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                    }
                                )

                                Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))

                                DropdownMenuItem(
                                    modifier = Modifier.testTag("delete-button"),
                                    onClick = onClickDelete,
                                    text = {
                                        Text(
                                            text = "Delete",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    }
                                )
                            }
                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Latitude: ${location.coordinate.latitude}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "Longitude: ${location.coordinate.longitude}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun LocationCardPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
        ) {
            LocationCard(
                modifier = Modifier
                    .padding(16.dp),
                location = Location.dummy
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LocationCardDarkPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.backgroundGradientEnd,
            modifier = Modifier
        ) {
            LocationCard(
                modifier = Modifier
                    .padding(16.dp),
                location = Location.dummy
            )
        }
    }
}