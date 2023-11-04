package dev.alfin.airmaps.modules.presentation.pages.search_location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.usecases.SearchLocationUseCaseInterface
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchLocationViewModel(private val homeUseCase: SearchLocationUseCaseInterface) : ViewModel() {
    var state by mutableStateOf(SearchLocationState())

    private var searchJob: Job? = null

    fun setKeyword(keyword: String) {
        state = state.copy(keyword = keyword)
    }

    fun searchLocation(keyword: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            if (keyword.isEmpty()) {
                state = state.copy(locationList = listOf())
            } else if (keyword.count() >= 3) {
                val locationList = homeUseCase.searchLocation(keyword)
                state = state.copy(locationList = locationList)
            }
        }
    }

}


data class SearchLocationState(
    var keyword: String = "",
    val locationList: List<Location> = listOf()
)
