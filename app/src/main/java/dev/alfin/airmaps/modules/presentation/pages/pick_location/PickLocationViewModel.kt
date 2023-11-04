package dev.alfin.airmaps.modules.presentation.pages.pick_location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dev.alfin.airmaps.modules.domain.entities.Coordinate


class PickLocationViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var state by mutableStateOf(PickLocationState())
        private set

    private val latitude: Double =
        checkNotNull(savedStateHandle[PickLocationDestination.latitudeArg])
    private val longitude: Double =
        checkNotNull(savedStateHandle[PickLocationDestination.longitudeArg])

    init {
        if (latitude != 0.0 && longitude != 0.0) {
            val coordinate = Coordinate(
                latitude = latitude,
                longitude = longitude
            )
            updateCoordinate(coordinate = coordinate)
        }
    }

    fun updateCoordinate(coordinate: Coordinate) {
        state = state.copy(
            coordinate
        )
    }

}


data class PickLocationState(
    val coordinate: Coordinate = Coordinate(-6.203472147782383, 106.82043483810568),
)

