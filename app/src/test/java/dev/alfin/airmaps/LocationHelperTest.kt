package dev.alfin.airmaps

import dev.alfin.airmaps.core.helpers.LocationHelper
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import org.junit.Assert.assertEquals
import org.junit.Test


class LocationHelperTest {

    @Test
    fun nearbyCoordinateTest() {
        val coordinate = Coordinate(
            latitude = 0.0,
            longitude = 0.0,
        )
        val generateCount = 6

        val coordinates = LocationHelper.nearbyCoordinate(
            coordinate,
            1000.0,
            generateCount
        )

        assertEquals(coordinates.count(), generateCount)
    }

}