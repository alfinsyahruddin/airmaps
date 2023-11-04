package dev.alfin.airmaps.modules.domain.usecases

import dev.alfin.airmaps.modules.data.repositories.AirQualityRepositoryInterface
import dev.alfin.airmaps.modules.data.repositories.LocationRepositoryInterface
import dev.alfin.airmaps.modules.domain.entities.AirQuality
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import dev.alfin.airmaps.modules.domain.entities.Location
import kotlinx.coroutines.flow.Flow

interface DetailLocationUseCaseInterface {
    fun getLocation(id: Int): Flow<Location?>
    suspend fun getAirQualities(coordinate: Coordinate): List<AirQuality>
}

class DetailLocationUseCase(
    private val locationRepository: LocationRepositoryInterface,
    private val airQualityRepositoryInterface: AirQualityRepositoryInterface
) : DetailLocationUseCaseInterface {
    override fun getLocation(id: Int): Flow<Location?> = locationRepository.getLocation(id)

    override suspend fun getAirQualities(coordinate: Coordinate): List<AirQuality> {
        return airQualityRepositoryInterface.getAirQualities(coordinate)
    }
}