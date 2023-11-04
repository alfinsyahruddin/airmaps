package dev.alfin.airmaps.modules.presentation.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.usecases.HomeUseCaseInterface
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(private val homeUseCase: HomeUseCaseInterface) : ViewModel() {
    val state: StateFlow<HomeState> =
        homeUseCase.getAllLocation().map { HomeState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = HomeState()
            )


    suspend fun deleteLocation(location: Location) {
        homeUseCase.deleteLocation(location)
    }

}


data class HomeState(
    val locationList: List<Location> = listOf()
)
