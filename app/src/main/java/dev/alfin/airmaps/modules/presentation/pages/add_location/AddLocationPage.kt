package dev.alfin.airmaps.modules.presentation.pages.add_location

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import dev.alfin.airmaps.R
import dev.alfin.airmaps.core.helpers.asLocation
import dev.alfin.airmaps.core.helpers.mainThread
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import dev.alfin.airmaps.modules.presentation.components.AppBar
import dev.alfin.airmaps.modules.presentation.components.CustomButton
import dev.alfin.airmaps.modules.presentation.components.CustomButtonType
import dev.alfin.airmaps.modules.presentation.components.CustomTextField
import dev.alfin.airmaps.modules.presentation.components.GradientBackground
import dev.alfin.airmaps.modules.presentation.navigation.NavigationDestination
import dev.alfin.airmaps.modules.presentation.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


object AddLocationDestination : NavigationDestination {
    override val route = "add_location"
    override val titleRes = R.string.add_location
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLocationPage(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    navigateToPickLocation: (Coordinate) -> Unit = {},
    navigateToSearchLocation: () -> Unit = {},
    viewModel: AddLocationViewModel = koinViewModel(),
    locationName: String? = null,
    latitude: Double? = null,
    longitude: Double? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("AIRMAPS", "PERMISSION GRANTED")
            viewModel.useCurrentLocation(context)
        } else {
            Log.d("AIRMAPS", "PERMISSION DENIED")
            Toast.makeText(context, "Location Permission denied!", Toast.LENGTH_SHORT).show()
        }
    }


    LaunchedEffect(locationName, latitude, longitude) {
        if (locationName != null) {
            viewModel.setForm(
                viewModel.state.form.copy(
                    name = locationName.toString(),
                )
            )
        }

        if (latitude != null && longitude != null) {
            viewModel.setForm(
                viewModel.state.form.copy(
                    latitude = latitude.toString(),
                    longitude = longitude.toString(),
                )
            )
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(AddLocationDestination.titleRes),
                canNavigateBack = true,
                onTapNavigateBack = navigateBack,
            )
        }
    ) { innerPadding ->
        GradientBackground {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                CustomTextField(
                    label = stringResource(R.string.location_name),
                    trailingIcon = {
                        Image(
                            modifier = Modifier
                                .clickable {
                                  navigateToSearchLocation()
                                },
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = stringResource(R.string.button_icon),
                            colorFilter = ColorFilter.tint(
                                MaterialTheme.colorScheme.onBackground.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                    },
                    text = viewModel.state.form.name,
                    onChange = { viewModel.setForm(viewModel.state.form.copy(name = it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("location-name")
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CustomTextField(
                        label = stringResource(R.string.latitude),
                        text = viewModel.state.form.latitude,
                        onChange = { viewModel.setForm(viewModel.state.form.copy(latitude = it)) },
                        keyboardType = KeyboardType.Decimal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .testTag("latitude")
                    )

                    CustomTextField(
                        label = stringResource(R.string.longitude),
                        text = viewModel.state.form.longitude,
                        onChange = { viewModel.setForm(viewModel.state.form.copy(longitude = it)) },
                        keyboardType = KeyboardType.Decimal,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .testTag("longitude")
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = CustomButtonType.OUTLINED,
                        icon = painterResource(R.drawable.ic_location),
                        text = stringResource(R.string.pick_location),
                        onClick = {
                            val location = viewModel.state.form.asLocation()
                            navigateToPickLocation(location.coordinate)
                        }
                    )

                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = CustomButtonType.OUTLINED,
                        icon = painterResource(R.drawable.ic_my_location),
                        text = stringResource(R.string.use_current_location),
                        onClick = {
                            // Check permission
                            when (PackageManager.PERMISSION_GRANTED) {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) -> {
                                    viewModel.useCurrentLocation(context)
                                }

                                else -> {
                                    // Request permission
                                    launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                }
                            }
                        }
                    )
                }

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("save-button"),
                    type = CustomButtonType.CONTAINED,
                    text = stringResource(R.string.save),
                    enabled = viewModel.state.isFormValidationSuccess,
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveLocation()

                            mainThread {
                                Toast.makeText(
                                    context,
                                    R.string.add_location_success,
                                    Toast.LENGTH_SHORT
                                ).show()

                                navigateBack()
                            }

                        }
                    }
                )

            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AddLocationPagePreview() {
    AppTheme {
        AddLocationPage()
    }
}

