package dev.alfin.airmaps.core.classes

import io.ktor.client.HttpClient
import io.ktor.client.engine.ProxyBuilder
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.http
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.network.tls.TLSConfigBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Paths
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager


class AppHttpClient {
    companion object {
        // For debugging with proxyman
        private const val enableProxy = false

        val jsonInstance = Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }

        fun provideHttpClient(): HttpClient {
            return HttpClient(CIO) {
                engine {

                    if (enableProxy) {
                        proxy = ProxyBuilder.http("http://192.168.100.172:9090/")
                    }

                    https {
                        trustManager = MyTrustManager(this)
                    }
                }

                install(Logging)

                install(HttpCache) {
                    val cacheFile = Files.createDirectories(Paths.get("cache")).toFile()
                    publicStorage(FileStorage(cacheFile))
                }

                install(ContentNegotiation) {
                    json(jsonInstance)
                }
            }
        }
    }
}


class MyTrustManager(private val config: TLSConfigBuilder) : X509TrustManager {
    private val delegate = config.build().trustManager

    override fun checkClientTrusted(certificates: Array<out X509Certificate>?, authType: String?) {}

    override fun checkServerTrusted(certificates: Array<out X509Certificate>?, authType: String?) {}

    override fun getAcceptedIssuers(): Array<X509Certificate> = delegate.acceptedIssuers
}