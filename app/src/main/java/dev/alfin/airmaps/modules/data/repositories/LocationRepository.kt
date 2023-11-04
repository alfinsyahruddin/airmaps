package dev.alfin.airmaps.modules.data.repositories

import dev.alfin.airmaps.modules.data.datasources.local.LocationDao
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.core.helpers.asLocation
import dev.alfin.airmaps.core.helpers.asLocationEntity
import dev.alfin.airmaps.modules.data.datasources.network.LocationServiceInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


interface LocationRepositoryInterface {
    fun getAllLocation(): Flow<List<Location>>
    fun getLocation(id: Int): Flow<Location?>
    suspend fun insertLocation(item: Location)
    suspend fun deleteLocation(item: Location)
    suspend fun updateLocation(item: Location)
    suspend fun searchLocation(keyword: String): List<Location>
}


class LocationRepository(
    private val locationDao: LocationDao,
    private val locationService: LocationServiceInterface
) : LocationRepositoryInterface {
    override fun getAllLocation(): Flow<List<Location>> {
        return flow {
            emitAll(locationDao.getAllLocation().map { list ->
                list.map { entity -> entity.asLocation() }
            })
        }
    }

    override fun getLocation(id: Int): Flow<Location?> {
        return flow {
            emitAll(locationDao.getLocation(id).map {
                it.asLocation()
            })
        }
    }

    override suspend fun insertLocation(location: Location) =
        locationDao.insert(location.asLocationEntity())

    override suspend fun deleteLocation(location: Location) =
        locationDao.delete(location.asLocationEntity())

    override suspend fun updateLocation(location: Location) =
        locationDao.update(location.asLocationEntity())

    override suspend fun searchLocation(keyword: String) =
        locationService.searchLocation(keyword).candidates.map { it.asLocation() }
}