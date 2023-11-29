package dev.alfin.airmaps.modules.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.presentation.theme.AppTheme


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    text: String = "",
    onChange: (String) -> Unit = {},
    singleLine: Boolean = true,
) {
    BasicTextField(
        modifier = modifier,
        singleLine = singleLine,
        value = text,
        onValueChange = onChange,
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.invoke()

                    Box {
                        if (text.isEmpty()) {
                            Text(
                                text = placeholder ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
                            )
                        }

                        innerTextField()
                    }


                }
            }
        }
    )
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    AppTheme {
        Surface(
            color = Color.White
        ) {
            Surface(
                modifier = Modifier.padding(16.dp)
            ) {
                SearchTextField(
                    placeholder = stringResource(R.string.enter_location_name)
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchTextFieldDarkPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Surface(
                modifier = Modifier.padding(16.dp)
            ) {
                SearchTextField(
                    placeholder = stringResource(R.string.enter_location_name)
                )
            }
        }
    }
}