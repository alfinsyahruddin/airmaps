package dev.alfin.airmaps.modules.presentation.pages.add_location

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.alfin.airmaps.core.helpers.LocationHelper
import dev.alfin.airmaps.core.helpers.asLocation
import dev.alfin.airmaps.modules.domain.usecases.AddLocationUseCaseInterface


class AddLocationViewModel(private val addLocationUseCase: AddLocationUseCaseInterface) :
    ViewModel() {
    var state by mutableStateOf(AddLocationState())
        private set

    fun setForm(form: AddLocationForm) {
        state = AddLocationState(form = form, isFormValidationSuccess = validateForm(form))
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
            addLocationUseCase.insertLocation(state.form.asLocation())
        }
    }

    private fun validateForm(form: AddLocationForm): Boolean {
        return with(form) {
            name.isNotBlank() && latitude.isNotBlank() && longitude.isNotBlank()
        }
    }

}


data class AddLocationState(
    val form: AddLocationForm = AddLocationForm(),
    val isFormValidationSuccess: Boolean = false,
)


data class AddLocationForm(
    val id: Int = 0,
    val name: String = "",
    val latitude: String = "",
    val longitude: String = "",
) {
    companion object
}

val AddLocationForm.Companion.dummy
    get() = AddLocationForm()



