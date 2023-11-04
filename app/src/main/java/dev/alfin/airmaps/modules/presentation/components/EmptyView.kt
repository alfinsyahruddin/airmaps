package dev.alfin.airmaps.modules.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier
            .semantics(mergeDescendants = true) {},
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = icon,
            contentDescription = stringResource(R.string.icon),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f))
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
            textAlign = TextAlign.Center
        )

    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun EmptyViewDarkPreview() {
    AppTheme {
        EmptyView(
            modifier = Modifier.padding(16.dp),
            icon = painterResource(R.drawable.ic_location_x),
            title = stringResource(R.string.empty_location),
            description = stringResource(R.string.empty_location_description)
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun EmptyViewPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                EmptyView(
                    icon = painterResource(R.drawable.ic_location_x),
                    title = stringResource(R.string.empty_location),
                    description = stringResource(R.string.empty_location_description)
                )
            }
        }

    }
}