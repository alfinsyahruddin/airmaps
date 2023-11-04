package dev.alfin.airmaps

import android.app.Application
import dev.alfin.airmaps.di.airQualityModule
import dev.alfin.airmaps.di.databaseModule
import dev.alfin.airmaps.di.homeModule
import dev.alfin.airmaps.di.httpClientModule
import dev.alfin.airmaps.di.locationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Dependency Injection
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    databaseModule,
                    httpClientModule,
                    airQualityModule,
                    locationModule,
                    homeModule,
                )
            )
        }
    }
}