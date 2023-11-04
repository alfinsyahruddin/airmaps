package dev.alfin.airmaps.modules.presentation.pages.edit_location

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.alfin.airmaps.core.helpers.LocationHelper
import dev.alfin.airmaps.core.helpers.asEditLocationForm
import dev.alfin.airmaps.core.helpers.asLocation
import dev.alfin.airmaps.modules.domain.usecases.EditLocationUseCaseInterface
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class EditLocationViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val editLocationUseCase: EditLocationUseCaseInterface
) :
    ViewModel() {
    var state by mutableStateOf(EditLocationState())
        private set

    private val id: Int = checkNotNull(savedStateHandle[EditLocationDestination.idArg])


    init {
        viewModelScope.launch {
            setForm(
                editLocationUseCase.getLocation(id)
                    .filterNotNull()
                    .first()
                    .asEditLocationForm()
            )
        }
    }

    fun setForm(form: EditLocationForm) {
        state = EditLocationState(form = form, isFormValidationSuccess = validateForm(form))
    }

    fun useCurrentLocation(context: Context) {
        LocationHelper.getCurrentLocation(context) { coordinate ->
            this.setForm(
                state.form.copy(
                    latitude = coordinate.latitude.toString(),
                    longitude = coordinate.longitude.toString(),
                )
            )
        }
    }


    suspend fun saveLocation() {
        if (validateForm(state.form)) {
            editLocationUseCase.updateLocation(state.form.asLocation())
        }
    }

    private fun validateForm(form: EditLocationForm): Boolean {
        return with(form) {
            name.isNotBlank() && latitude.isNotBlank() && longitude.isNotBlank()
        }
    }
}


data class EditLocationState(
    val form: EditLocationForm = EditLocationForm(),
    val isFormValidationSuccess: Boolean = false,
)


data class EditLocationForm(
    val id: Int = 0,
    val name: String = "",
    val latitude: String = "",
    val longitude: String = "",
) {
    companion object
}

val EditLocationForm.Companion.dummy
    get() = EditLocationForm()