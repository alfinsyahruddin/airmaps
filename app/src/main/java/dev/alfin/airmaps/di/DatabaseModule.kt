package dev.alfin.airmaps.di

import dev.alfin.airmaps.core.classes.AppDatabase
import dev.alfin.airmaps.modules.data.datasources.local.LocationDao
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        AppDatabase.getDatabase(androidContext())
    }

    singleOf(::provideLocationDao)
}

private fun provideLocationDao(appDatabase: AppDatabase): LocationDao {
    return appDatabase.getLocationDao()
}
