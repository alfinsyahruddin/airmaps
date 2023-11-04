package dev.alfin.airmaps

import dev.alfin.airmaps.core.classes.AppHttpClient
import dev.alfin.airmaps.modules.data.entities.responses.AirQualityResponse
import dev.alfin.airmaps.modules.data.entities.responses.LocationResponse
import org.junit.Assert.assertTrue
import org.junit.Test

class DataSerializationTest {
    @Test
    fun locationResponseTest() {
        val json = readFile("search_location_response.json")
        val locationResponse = AppHttpClient.jsonInstance.decodeFromString<LocationResponse>(json)

        assertTrue(locationResponse is LocationResponse)
    }

    @Test
    fun airQualityResponseTest() {
        val json = readFile("air_quality_response.json")
        val airQualityResponse = AppHttpClient.jsonInstance.decodeFromString<AirQualityResponse>(json)

        assertTrue(airQualityResponse is AirQualityResponse)
    }

    private fun readFile(path: String): String {
        return this::class.java.classLoader.getResource(path).readText()
    }
}
