package dev.alfin.airmaps.di

import dev.alfin.airmaps.modules.data.repositories.LocationRepository
import dev.alfin.airmaps.modules.data.repositories.LocationRepositoryInterface
import dev.alfin.airmaps.modules.domain.usecases.AddLocationUseCase
import dev.alfin.airmaps.modules.domain.usecases.AddLocationUseCaseInterface
import dev.alfin.airmaps.modules.domain.usecases.EditLocationUseCase
import dev.alfin.airmaps.modules.domain.usecases.EditLocationUseCaseInterface
import dev.alfin.airmaps.modules.data.datasources.network.LocationService
import dev.alfin.airmaps.modules.data.datasources.network.LocationServiceInterface
import dev.alfin.airmaps.modules.domain.usecases.DetailLocationUseCase
import dev.alfin.airmaps.modules.domain.usecases.DetailLocationUseCaseInterface
import dev.alfin.airmaps.modules.domain.usecases.SearchLocationUseCase
import dev.alfin.airmaps.modules.domain.usecases.SearchLocationUseCaseInterface
import dev.alfin.airmaps.modules.presentation.pages.detail_location.DetailLocationViewModel
import dev.alfin.airmaps.modules.presentation.pages.search_location.SearchLocationViewModel
import dev.alfin.airmaps.modules.presentation.pages.add_location.AddLocationViewModel
import dev.alfin.airmaps.modules.presentation.pages.edit_location.EditLocationViewModel
import dev.alfin.airmaps.modules.presentation.pages.pick_location.PickLocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val locationModule = module {
    singleOf(::LocationService) { bind<LocationServiceInterface>() }

    singleOf(::LocationRepository) { bind<LocationRepositoryInterface>() }

    viewModelOf(::PickLocationViewModel)

    singleOf(::AddLocationUseCase) { bind<AddLocationUseCaseInterface>() }
    viewModelOf(::AddLocationViewModel)

    singleOf(::EditLocationUseCase) { bind<EditLocationUseCaseInterface>() }
    viewModelOf(::EditLocationViewModel)

    singleOf(::DetailLocationUseCase) { bind<DetailLocationUseCaseInterface>() }
    viewModelOf(::DetailLocationViewModel)

    singleOf(::SearchLocationUseCase) { bind<SearchLocationUseCaseInterface>() }
    viewModelOf(::SearchLocationViewModel)
}
