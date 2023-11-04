package dev.alfin.airmaps.modules.data.repositories


import dev.alfin.airmaps.core.helpers.LocationHelper
import dev.alfin.airmaps.core.helpers.asAirQuality
import dev.alfin.airmaps.modules.data.datasources.network.AirQualityService
import dev.alfin.airmaps.modules.domain.entities.AirQuality
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope


interface AirQualityRepositoryInterface {
    suspend fun getAirQualities(coordinate: Coordinate, total: Int = 6): List<AirQuality>
    suspend fun getAirQuality(coordinate: Coordinate): AirQuality?
}


class AirQualityRepository(private val airQualityService: AirQualityService) : AirQualityRepositoryInterface {

    override suspend fun getAirQualities(coordinate: Coordinate, total: Int): List<AirQuality> {
        val coordinates = listOf(coordinate) + LocationHelper.nearbyCoordinate(coordinate, 3000.0, total)

        // Fetch AirQuality API in Parallel ⚡️
        val result = coroutineScope {
            val airQualities = coordinates.map { coordinate ->
                async { this@AirQualityRepository.getAirQuality(coordinate) }
            }.awaitAll()

            return@coroutineScope airQualities
        }

        return result.filterNotNull()
    }

    override suspend fun getAirQuality(coordinate: Coordinate): AirQuality? {
        return airQualityService.getAirQuality(coordinate).asAirQuality(coordinate)
    }

}