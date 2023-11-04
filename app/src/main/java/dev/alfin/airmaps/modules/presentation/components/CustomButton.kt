package dev.alfin.airmaps.modules.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.presentation.theme.AppTheme
import dev.alfin.airmaps.modules.presentation.theme.disabledContainer
import dev.alfin.airmaps.modules.presentation.theme.onDisabledContainer

enum class CustomButtonType {
    CONTAINED, OUTLINED
}

fun Modifier.modifyIf(condition: Boolean, modify: Modifier.() -> Modifier) =
    if (condition) modify() else this

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    type: CustomButtonType = CustomButtonType.CONTAINED,
    text: String,
    icon: Painter? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .height(48.dp),
        border = if (type == CustomButtonType.OUTLINED) BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ) else null,
        colors = if (type == CustomButtonType.OUTLINED) ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        ) else ButtonDefaults.filledTonalButtonColors( // CONTAINED
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.disabledContainer,
            disabledContentColor = MaterialTheme.colorScheme.onDisabledContainer
        ),
        enabled = enabled,
        onClick = onClick
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Image(
                    painter = icon,
                    contentDescription = stringResource(R.string.button_icon),
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(
                        if (type == CustomButtonType.OUTLINED)
                            if (!enabled)
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                            else MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onPrimary
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))
            }


            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    AppTheme {
        CustomButton(
            type = CustomButtonType.CONTAINED,
            text = stringResource(R.string.pick_location),
            icon = painterResource(id = R.drawable.ic_my_location),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun CustomButtonOutlinedPreview() {
    AppTheme {
        CustomButton(
            type = CustomButtonType.OUTLINED,
            text = stringResource(R.string.pick_location),
            icon = painterResource(id = R.drawable.ic_my_location),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}