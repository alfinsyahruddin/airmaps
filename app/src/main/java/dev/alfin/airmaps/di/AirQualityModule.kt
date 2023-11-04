package dev.alfin.airmaps.di

import dev.alfin.airmaps.modules.data.datasources.network.AirQualityService
import dev.alfin.airmaps.modules.data.datasources.network.AirQualityServiceInterface
import dev.alfin.airmaps.modules.data.repositories.AirQualityRepository
import dev.alfin.airmaps.modules.data.repositories.AirQualityRepositoryInterface
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val airQualityModule = module {
    singleOf(::AirQualityRepository) { bind<AirQualityRepositoryInterface>() }
    singleOf(::AirQualityService) { bind<AirQualityServiceInterface>() }
}