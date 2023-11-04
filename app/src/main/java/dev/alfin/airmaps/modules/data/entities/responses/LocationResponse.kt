package dev.alfin.airmaps.modules.data.entities.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class LocationResponse(
    val candidates: List<LocationCandidateResponse>
) {
    companion object {}
}

@Serializable
data class LocationCandidateResponse(
    val name: String,

    @SerialName("formatted_address")
    val formattedAddress: String,

    val geometry: Geometry,
) {
    companion object
}

@Serializable
data class Geometry(
    val location: LocationLatLng,
)

@Serializable
data class LocationLatLng(
    val lat: Double,
    val lng: Double
)

val LocationResponse.Companion.dummy: LocationResponse
    get() = LocationResponse(
        candidates = listOf(
            LocationCandidateResponse.dummy
        )
    )

val LocationCandidateResponse.Companion.dummy: LocationCandidateResponse
    get() = LocationCandidateResponse(
        name = "Benhil ${Random.nextInt()}",
        formattedAddress = "Bendungan Hilir",
        geometry = Geometry(
            location = LocationLatLng(
                lat = 5.0,
                lng = 10.0
            )
        ),
    )