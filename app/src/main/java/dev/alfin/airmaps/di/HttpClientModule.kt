package dev.alfin.airmaps.di

import dev.alfin.airmaps.core.classes.AppHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val httpClientModule = module {
    singleOf(AppHttpClient::provideHttpClient)
}