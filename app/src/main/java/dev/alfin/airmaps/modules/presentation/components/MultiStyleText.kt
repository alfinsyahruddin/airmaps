package dev.alfin.airmaps.modules.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MultiStyleText(vararg textList: TextItem) {
    Text(
        buildAnnotatedString {
            textList.forEach { item ->
                withStyle(
                    style = SpanStyle(
                        color = item.color,
                        fontFamily = item.style.fontFamily,
                        fontSize = item.style.fontSize,
                        fontWeight = item.style.fontWeight
                    )
                ) {
                    append(item.text)
                }
            }
        }
    )
}

data class TextItem(
    val text: String,
    val color: Color,
    val style: TextStyle
)

@Preview
@Composable
fun MultiStyleTextPreview() {
    MultiStyleText(
        TextItem(
            text = "Color: ",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        ),
        TextItem(
            text = "Red",
            color = Color.Red,
            style = MaterialTheme.typography.titleSmall
        )
    )
}