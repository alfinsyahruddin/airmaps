package dev.alfin.airmaps

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.alfin.airmaps.modules.presentation.components.CircleText
import org.junit.Rule
import org.junit.Test

class CircleTextUITest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun emptyStateTest() {
        rule.setContent {
            CircleText(text = "100", color = Color.Black)
        }

        rule.onNodeWithText("100").assertIsDisplayed()
    }
}