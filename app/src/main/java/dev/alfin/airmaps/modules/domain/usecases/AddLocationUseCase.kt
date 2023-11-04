package dev.alfin.airmaps.modules.domain.usecases

import dev.alfin.airmaps.modules.data.repositories.LocationRepositoryInterface
import dev.alfin.airmaps.modules.domain.entities.Location

interface AddLocationUseCaseInterface {
    suspend fun insertLocation(location: Location)
}

class AddLocationUseCase(
    private val locationRepository: LocationRepositoryInterface
) : AddLocationUseCaseInterface {
    override suspend fun insertLocation(location: Location) =
        locationRepository.insertLocation(location)
}