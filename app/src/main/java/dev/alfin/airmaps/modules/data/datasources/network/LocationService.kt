package dev.alfin.airmaps.modules.data.datasources.network

import dev.alfin.airmaps.BuildConfig
import dev.alfin.airmaps.modules.data.entities.responses.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.io.File

interface LocationServiceInterface {
    suspend fun searchLocation(keyword: String): LocationResponse
}

fun getLocalProperty(key: String, file: String = "local.properties"): Any {
    val properties = java.util.Properties()
    val localProperties = File(file)
    if (localProperties.isFile) {
        java.io.InputStreamReader(java.io.FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else error("File from not found")

    return properties.getProperty(key)
}

class LocationService(
    private val httpClient: HttpClient
): LocationServiceInterface {
    override suspend fun searchLocation(keyword: String): LocationResponse {
        return httpClient.get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json") {
            url {
                parameters.append("input", keyword)
                parameters.append("inputtype", "textquery")
                parameters.append("fields", "formatted_address,name,geometry")
                parameters.append("key", BuildConfig.google_maps_api_key)
            }
        }.body()
    }
}
