package dev.alfin.airmaps.modules.domain.entities

import androidx.compose.ui.graphics.Color

data class AirQuality(
    val coordinate: Coordinate,
    val status: String,
    val value: Int,
    val dominantPollutant: String,
    val pm25Value: Double,
    val healthRecommendation: String
) {
    companion object
}

val AirQuality.color: Color
    get() = when (this.status) {
        "Excellent air quality" -> Color(0xFFABD162)
        "Good air quality" -> Color(0xFFABD162)
        "Moderate air quality" -> Color(0xFFF7D460)
        "Low air quality" -> Color(0xFFFC9956)
        "Poor air quality" -> Color(0xFFF6676B)
        else -> Color.Gray
    }

val AirQuality.pm25Status: String
    get() = when (pm25Value) {
        in 0.0..12.0 -> {
            "Good"
        }

        in 12.1..35.4 -> {
            "Moderate"
        }

        in 35.5..55.4 -> {
            "Unhealthy for Sensitive Groups"
        }

        in 55.5..150.4 -> {
            "Unhealthy"
        }

        in 150.5..250.4 -> {
            "Very Unhealthy"
        }

        else -> {
            "Hazardous"
        }
    }

val AirQuality.pm25Color: Color
    get() = when (this.pm25Status) {
        "Good" -> Color(0xFFABD162)
        "Moderate" -> Color(0xFFABD162)
        "Unhealthy for Sensitive Groups" -> Color(0xFFF7D460)
        "Unhealthy" -> Color(0xFFFC9956)
        "Very Unhealthy" -> Color(0xFFF6676B)
        "Hazardous" -> Color(0xFFA37DB8)
        else -> Color.Gray
    }


val AirQuality.Companion.dummy: AirQuality
    get() = AirQuality(
        coordinate = Coordinate.dummy,
        status = "Low air quality",
        value = 28,
        dominantPollutant = "pm25",
        pm25Value = 82.95,
        healthRecommendation = "Reduce the intensity of your outdoor activities or postpone them to the early morning when ozone levels tend to be lower. It is recommended to limit the time you are near busy roads, construction sites, open fires and other sources of smoke. Staying indoors with an activated air filtration system would be best for your long term health.",
    )