package dev.alfin.airmaps.modules.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Coordinate(
    val latitude: Double,
    val longitude: Double
) {
    companion object
}

val Coordinate.Companion.dummy: Coordinate
    get() = Coordinate(
        latitude = -6.33224394389,
        longitude = 114.33224394389
    )