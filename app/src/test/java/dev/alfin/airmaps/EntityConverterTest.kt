package dev.alfin.airmaps

import com.google.android.gms.maps.model.LatLng
import dev.alfin.airmaps.core.helpers.asAirQuality
import dev.alfin.airmaps.core.helpers.asCoordinate
import dev.alfin.airmaps.core.helpers.asDouble
import dev.alfin.airmaps.core.helpers.asEditLocationForm
import dev.alfin.airmaps.core.helpers.asLatLng
import dev.alfin.airmaps.core.helpers.asLocation
import dev.alfin.airmaps.core.helpers.asLocationEntity
import dev.alfin.airmaps.modules.data.entities.database.LocationEntity
import dev.alfin.airmaps.modules.data.entities.database.dummy
import dev.alfin.airmaps.modules.data.entities.responses.AirQualityResponse
import dev.alfin.airmaps.modules.data.entities.responses.LocationCandidateResponse
import dev.alfin.airmaps.modules.data.entities.responses.dummy
import dev.alfin.airmaps.modules.domain.entities.AirQuality
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.entities.dummy
import dev.alfin.airmaps.modules.presentation.pages.add_location.AddLocationForm
import dev.alfin.airmaps.modules.presentation.pages.add_location.dummy
import dev.alfin.airmaps.modules.presentation.pages.edit_location.EditLocationForm
import dev.alfin.airmaps.modules.presentation.pages.edit_location.dummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EntityConverterTest {


    @Test
    fun convertLocationEntityToLocationTest() {
        val locationEntity = LocationEntity.dummy
        val location = locationEntity.asLocation()

        assertTrue(location is Location)
    }

    @Test
    fun convertLocationToLocationEntityTest() {
        val location = Location.dummy
        val locationEntity = location.asLocationEntity()

        assertTrue(locationEntity is LocationEntity)
    }

    @Test
    fun convertAddLocationFormToLocationTest() {
        val form = AddLocationForm.dummy
        val location = form.asLocation()

        assertTrue(location is Location)
    }

    @Test
    fun convertLatLngToCoordinateTest() {
        val latLng = LatLng(0.0, 0.0)
        val coordinate = latLng.asCoordinate()

        assertTrue(coordinate is Coordinate)
    }

    @Test
    fun convertCoordinateToLatLngTest() {
        val coordinate = Coordinate.dummy
        val latLng = coordinate.asLatLng()

        assertTrue(latLng is LatLng)
    }

    @Test
    fun convertEditLocationFormToLocationTest() {
        val form = EditLocationForm.dummy
        val location = form.asLocation()

        assertTrue(location is Location)
    }

    @Test
    fun convertLocationToEditLocationFormTest() {
        val location = Location.dummy
        val form = location.asEditLocationForm()

        assertTrue(form is EditLocationForm)
    }

    @Test
    fun convertStringToDoubleTest() {
        val text = "0,123"
        val double = text.asDouble()

        assertTrue(double is Double)
        assertEquals(double, 0.123, 0.001)
    }

    @Test
    fun convertLocationCandidateResponseToLocationTest() {
        val locationCandidateResponse = LocationCandidateResponse.dummy
        val location = locationCandidateResponse.asLocation()

        assertTrue(location is Location)
    }

    @Test
    fun convertAirQualityResponseToAirQualityTest() {
        val airQualityResponse = AirQualityResponse.dummy
        val airQuality = airQualityResponse.asAirQuality(Coordinate.dummy)

        assertTrue(airQuality is AirQuality)
    }


}