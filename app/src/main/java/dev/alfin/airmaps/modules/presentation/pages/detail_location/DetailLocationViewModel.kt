package dev.alfin.airmaps.modules.presentation.pages.detail_location

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alfin.airmaps.modules.domain.entities.AirQuality
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.domain.entities.placeholder
import dev.alfin.airmaps.modules.domain.usecases.DetailLocationUseCaseInterface
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class DetailLocationViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val detailLocationUseCase: DetailLocationUseCaseInterface,
) : ViewModel() {
    var state by mutableStateOf(DetailLocationState())
        private set

    private val id: Int = checkNotNull(savedStateHandle[DetailLocationDestination.idArg])

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            // 1. Get Location Detail
            val location = detailLocationUseCase.getLocation(id)
                .filterNotNull()
                .first()

            state = state.copy(
                location = location,
            )

            // 2. Get Air Quality in that location.
            val airQualities = detailLocationUseCase.getAirQualities(coordinate = location.coordinate)

            state = state.copy(
                airQualities = airQualities,
                selectedAirQuality = airQualities.filter {
                    it.coordinate == location.coordinate
                }.firstOrNull(),
                isLoading = false
            )
        }
    }


    fun setState(getNewState: (DetailLocationState) -> DetailLocationState) {
        this.state = getNewState(this.state)

        Log.d("AIRMAPS", this.state.isShowAirQualitySheet.toString())
    }

}


data class DetailLocationState(
    val location: Location = Location.placeholder,
    val airQualities: List<AirQuality> = listOf(),
    val selectedAirQuality: AirQuality? = null,
    val isShowAirQualitySheet: Boolean = true,
    val isLoading: Boolean = true
)


