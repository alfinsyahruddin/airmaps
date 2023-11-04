package dev.alfin.airmaps.modules.data.entities.responses

import kotlinx.serialization.Serializable


@Serializable
data class AirQualityResponse(
    val indexes: List<AQI>,
    val pollutants: List<Pollutant>,
    val healthRecommendations: HealthRecommendations
) {
    companion object {}
}

@Serializable
data class HealthRecommendations(
    val generalPopulation: String
)

@Serializable
data class AQI(
    val code: String,
    val displayName: String,
    val aqi: Int,
    val aqiDisplay: String,
    val category: String,
    val dominantPollutant: String
)

@Serializable
data class Pollutant(
    val code: String,
    val displayName: String,
    val fullName: String,
    val concentration: Concentration,
)


@Serializable
data class Concentration(
    val value: Double,
    val units: String
)

val AirQualityResponse.Companion.dummy
    get() = AirQualityResponse(
        indexes = listOf(
            AQI(
                code = "uaqi",
                displayName = "Universal AQI",
                aqi = 24,
                aqiDisplay = "24",
                category = "Low air quality",
                dominantPollutant = "pm25"
            )
        ),
        pollutants = listOf(
            Pollutant(
                code = "pm25",
                displayName = "PM2.5",
                fullName = "PM 25",
                concentration = Concentration(
                    value = 74.0,
                    units = "PARTS_PER_BILLION"
                )
            ),
        ),
        healthRecommendations = HealthRecommendations(
            generalPopulation = "Reduce the intensity of your outdoor activities. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. In addition, consider reducing the time you spend near industrial emission stacks. Staying indoors with an activated air filtration system would be best for your long term health."
        )
    )

