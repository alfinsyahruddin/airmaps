package dev.alfin.airmaps.modules.data.datasources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.alfin.airmaps.modules.data.entities.database.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * from locations ORDER BY id DESC")
    fun getAllLocation(): Flow<List<LocationEntity>>

    @Query("SELECT * from locations WHERE id = :id")
    fun getLocation(id: Int): Flow<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: LocationEntity)

    @Update
    suspend fun update(item: LocationEntity)

    @Delete
    suspend fun delete(item: LocationEntity)
}