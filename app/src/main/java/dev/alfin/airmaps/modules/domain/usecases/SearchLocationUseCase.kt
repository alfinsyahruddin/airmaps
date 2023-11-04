package dev.alfin.airmaps.modules.domain.usecases


import dev.alfin.airmaps.modules.data.repositories.LocationRepositoryInterface
import dev.alfin.airmaps.modules.domain.entities.Location

interface SearchLocationUseCaseInterface {
    suspend fun searchLocation(keyword: String): List<Location>
}

class SearchLocationUseCase(
    private val locationRepository: LocationRepositoryInterface
) : SearchLocationUseCaseInterface {
    override suspend fun searchLocation(keyword: String): List<Location> = locationRepository.searchLocation(keyword)
}