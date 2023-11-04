package dev.alfin.airmaps.modules.domain.usecases

import dev.alfin.airmaps.modules.data.repositories.LocationRepositoryInterface
import dev.alfin.airmaps.modules.domain.entities.Location
import kotlinx.coroutines.flow.Flow

interface HomeUseCaseInterface {
    fun getAllLocation(): Flow<List<Location>>
    suspend fun deleteLocation(location: Location)
}

class HomeUseCase(
    private val locationRepository: LocationRepositoryInterface
) : HomeUseCaseInterface {
    override fun getAllLocation(): Flow<List<Location>> = locationRepository.getAllLocation()
    override suspend fun deleteLocation(location: Location) =
        locationRepository.deleteLocation(location)
}