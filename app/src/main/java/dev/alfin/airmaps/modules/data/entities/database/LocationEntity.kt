package dev.alfin.airmaps.modules.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val latitude: Double,

    val longitude: Double
) {
    companion object
}

val LocationEntity.Companion.dummy: LocationEntity
    get() = LocationEntity(
        name = "Jakarta",
        latitude = 0.0,
        longitude = 0.0,
    )