package dev.alfin.airmaps

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.entities.dummy
import dev.alfin.airmaps.modules.presentation.MainActivity
import org.junit.Rule
import org.junit.Test


class LocationUITest {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun createLocationTest() {
        // Empty State on initial
        assertEmptyData()

        // Create Location
        createLocation()

        // Reset data
        deleteLocation()

        // Empty State
        assertEmptyData()
    }


    @Test
    fun editLocationTest() {
        // Create Location first
        createLocation()

        // Click "More" Button
        rule.onNodeWithTag("more-button").performClick()

        // Click "Edit" button
        rule.onNodeWithTag("edit-button").performClick()

        // Edit Location Name
        rule.onNodeWithTag("location-name").performTextReplacement("NYC")

        // Click "Save" button
        rule.onNodeWithTag("save-button").performClick()

        // Make sure the data displayed in the home page
        rule.onNodeWithText("NYC").assertExists()

        // Reset data
        deleteLocation()

        // Empty State
        assertEmptyData()
    }


    @Test
    fun deleteLocationTest() {
        // Create Location first
        createLocation()

        // Delete Location
        deleteLocation()

        // Make sure the data is removed from the home page
        assertEmptyData()
    }


    private fun assertEmptyData() {
        // Empty State on initial
        val emptyTitle = rule.activity.getString(R.string.empty_location)
        rule.onNodeWithText(emptyTitle).assertExists()
    }

    private fun createLocation(location: Location = Location.dummy) {
        // Click + Button
        rule.onNodeWithTag("add-location-button").performClick()

        // Fill the form
        rule.onNodeWithTag("location-name").performTextInput(location.name)
        rule.onNodeWithTag("latitude").performTextInput(location.coordinate.latitude.toString())
        rule.onNodeWithTag("longitude").performTextInput(location.coordinate.longitude.toString())

        // Click "Save" button
        rule.onNodeWithTag("save-button").performClick()

        // Make sure the data displayed in the home page
        rule.onNodeWithText(location.name).assertIsDisplayed()
    }

    private fun deleteLocation() {
        // Click "More" Button
        rule.onNodeWithTag("more-button").performClick()

        // Click "Delete" button
        rule.onNodeWithTag("delete-button").performClick()
    }

}