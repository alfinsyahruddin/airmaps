package dev.alfin.airmaps.modules.data.datasources.network


import dev.alfin.airmaps.BuildConfig
import dev.alfin.airmaps.modules.data.entities.requests.AirQualityRequest
import dev.alfin.airmaps.modules.data.entities.responses.AirQualityResponse
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface AirQualityServiceInterface {
    suspend fun getAirQuality(coordinate: Coordinate): AirQualityResponse
}

class AirQualityService(
    private val httpClient: HttpClient
) : AirQualityServiceInterface {
    override suspend fun getAirQuality(coordinate: Coordinate): AirQualityResponse {
        return httpClient.post("https://airquality.googleapis.com/v1/currentConditions:lookup") {
            url {
                parameters.append("key", BuildConfig.google_maps_api_key)
            }

            val body = AirQualityRequest(
                universalAqi = true,
                location = coordinate,
                extraComputations = listOf(
                    "HEALTH_RECOMMENDATIONS",
                    "DOMINANT_POLLUTANT_CONCENTRATION",
                    "POLLUTANT_CONCENTRATION",
                    "LOCAL_AQI",
                    "POLLUTANT_ADDITIONAL_INFO"
                ),
                languageCode = "en"
            )

            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }
}