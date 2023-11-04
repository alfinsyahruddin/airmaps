package dev.alfin.airmaps.modules.data.entities.requests

import dev.alfin.airmaps.modules.domain.entities.Coordinate
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityRequest(
    val universalAqi: Boolean,
    val location: Coordinate,
    val extraComputations: List<String>,
    val languageCode: String
)