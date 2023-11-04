package dev.alfin.airmaps.modules.domain.usecases


import dev.alfin.airmaps.modules.data.repositories.LocationRepositoryInterface
import dev.alfin.airmaps.modules.domain.entities.Location
import kotlinx.coroutines.flow.Flow

interface EditLocationUseCaseInterface {
    fun getLocation(id: Int): Flow<Location?>
    suspend fun updateLocation(location: Location)
}

class EditLocationUseCase(
    private val locationRepository: LocationRepositoryInterface
) : EditLocationUseCaseInterface {

    override fun getLocation(id: Int): Flow<Location?> = locationRepository.getLocation(id)

    override suspend fun updateLocation(location: Location) =
        locationRepository.updateLocation(location)
}