package dev.alfin.airmaps.di

import dev.alfin.airmaps.modules.domain.usecases.HomeUseCase
import dev.alfin.airmaps.modules.domain.usecases.HomeUseCaseInterface
import dev.alfin.airmaps.modules.presentation.pages.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeModule = module {
    singleOf(::HomeUseCase) { bind<HomeUseCaseInterface>() }
    viewModelOf(::HomeViewModel)
}