package dev.alfin.airmaps.core.helpers

import com.google.android.gms.maps.model.LatLng
import dev.alfin.airmaps.modules.data.entities.database.LocationEntity
import dev.alfin.airmaps.modules.data.entities.responses.AirQualityResponse
import dev.alfin.airmaps.modules.data.entities.responses.LocationCandidateResponse
import dev.alfin.airmaps.modules.domain.entities.AirQuality
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.presentation.pages.add_location.AddLocationForm
import dev.alfin.airmaps.modules.presentation.pages.edit_location.EditLocationForm
import kotlin.random.Random

fun LocationEntity.asLocation(): Location {
    return Location(
        id = this.id,
        name = this.name,
        coordinate = Coordinate(
            latitude = this.latitude,
            longitude = this.longitude
        )
    )
}

fun Location.asLocationEntity(): LocationEntity {
    return LocationEntity(
        id = this.id,
        name = this.name,
        latitude = this.coordinate.latitude,
        longitude = this.coordinate.longitude
    )
}

fun AddLocationForm.asLocation(): Location {
    return Location(
        id = this.id,
        name = this.name,
        coordinate = Coordinate(
            latitude = this.latitude.asDouble(),
            longitude = this.longitude.asDouble(),
        )
    )
}


fun LatLng.asCoordinate(): Coordinate {
    return Coordinate(
        latitude = this.latitude,
        longitude = this.longitude
    )
}


fun Coordinate.asLatLng(): LatLng {
    return LatLng(
        this.latitude,
        this.longitude
    )
}

fun EditLocationForm.asLocation(): Location {
    return Location(
        id = this.id,
        name = this.name,
        coordinate = Coordinate(
            latitude = this.latitude.asDouble(),
            longitude = this.longitude.asDouble(),
        )
    )
}

fun Location.asEditLocationForm(): EditLocationForm {
    return EditLocationForm(
        id = this.id,
        name = this.name,
        latitude = this.coordinate.latitude.toString(),
        longitude = this.coordinate.longitude.toString(),
    )
}

fun String.asDouble(): Double {
    return this.replace(",", ".").toDoubleOrNull() ?: 0.0
}

fun LocationCandidateResponse.asLocation(): Location {
    return Location(
        id = Random.nextInt(),
        name = this.name,
        description = this.formattedAddress,
        coordinate = Coordinate(
            latitude = this.geometry.location.lat,
            longitude = this.geometry.location.lng,
        ),
    )
}

fun AirQualityResponse.asAirQuality(coordinate: Coordinate): AirQuality? {
    val uaqi = this.indexes.firstOrNull { it.code == "uaqi"}
    val pm25 = this.pollutants.firstOrNull { it.code == "pm25"}

    if (uaqi != null && pm25 != null) {
        return AirQuality(
            coordinate = coordinate,
            status = uaqi.category,
            value = uaqi.aqi,
            dominantPollutant = uaqi.dominantPollutant,
            pm25Value = pm25.concentration.value,
            healthRecommendation = this.healthRecommendations.generalPopulation
        )
    } else {
        return null
    }
}