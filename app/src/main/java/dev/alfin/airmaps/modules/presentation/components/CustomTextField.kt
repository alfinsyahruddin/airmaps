package dev.alfin.airmaps.modules.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    label: String = "",
    placeholder: String? = null,
    text: String,
    onChange: ((String) -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val isFocused = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.onFocusChanged {
            isFocused.value = it.isFocused
        },
        singleLine = singleLine,
        label = {
            if (label.isNotBlank()) {
                if (isFocused.value) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleSmall
                    )
                } else {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = placeholder ?: label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25F)
            )
        },
        value = text,
        textStyle = MaterialTheme.typography.bodyMedium,
        onValueChange = {
            onChange?.invoke(it)
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
fun CustomTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            Surface(
                modifier = Modifier.padding(16.dp),
            ) {
                CustomTextField(
                    label = stringResource(R.string.location_name),
                    text = text,
                    trailingIcon = {
                        Image(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.button_icon),
                            colorFilter = ColorFilter.tint(
                                MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.5F
                                )
                            )
                        )
                    },
                    onChange = {
                        text = it
                    },
                )
            }
        }
    }
}