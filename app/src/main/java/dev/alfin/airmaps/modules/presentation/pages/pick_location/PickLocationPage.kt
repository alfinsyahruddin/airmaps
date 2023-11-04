package dev.alfin.airmaps.modules.presentation.pages.pick_location

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.alfin.airmaps.R
import dev.alfin.airmaps.core.helpers.asCoordinate
import dev.alfin.airmaps.core.helpers.asLatLng
import dev.alfin.airmaps.modules.domain.entities.Coordinate
import dev.alfin.airmaps.modules.presentation.components.AppBar
import dev.alfin.airmaps.modules.presentation.components.CustomButton
import dev.alfin.airmaps.modules.presentation.components.CustomButtonType
import dev.alfin.airmaps.modules.presentation.navigation.NavigationDestination
import dev.alfin.airmaps.modules.presentation.pages.pick_location.components.CoordinateCard
import org.koin.androidx.compose.koinViewModel

object PickLocationDestination : NavigationDestination {
    override val route = "pick_location"
    override val titleRes = R.string.pick_location

    const val latitudeArg = "latitude"
    const val longitudeArg = "longitude"
    val routeWithArgs = "$route/{$latitudeArg}/{$longitudeArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickLocationPage(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onPickLocation: (Coordinate) -> Unit = {},
    viewModel: PickLocationViewModel = koinViewModel()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.state.coordinate.asLatLng(), 13f)
    }

    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(PickLocationDestination.titleRes),
                canNavigateBack = true,
                onTapNavigateBack = navigateBack,
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.BottomCenter
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = {
                    viewModel.updateCoordinate(it.asCoordinate())
                }
            ) {
                // Handle Dark Mode
                MapEffect(isSystemInDarkTheme()) { map ->
                    val style = MapStyleOptions.loadRawResourceStyle(
                        context,
                        if (isDark) R.raw.dark_map else R.raw.light_map
                    )
                    map.setMapStyle(style)
                }

                Marker(
                    state = MarkerState(position = viewModel.state.coordinate.asLatLng())
                )
            }

            Column {
                CoordinateCard(
                    modifier = Modifier
                        .padding(16.dp),
                    coordinate = viewModel.state.coordinate
                )

                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        type = CustomButtonType.CONTAINED,
                        text = stringResource(R.string.pick_location),
                        onClick = {
                            onPickLocation(viewModel.state.coordinate)
                        }
                    )
                }
            }
        }
    }

}