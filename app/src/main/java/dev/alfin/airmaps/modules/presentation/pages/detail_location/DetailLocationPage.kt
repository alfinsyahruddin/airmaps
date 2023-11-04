package dev.alfin.airmaps.modules.presentation.pages.detail_location

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.alfin.airmaps.R
import dev.alfin.airmaps.core.helpers.asLatLng
import dev.alfin.airmaps.modules.domain.entities.color
import dev.alfin.airmaps.modules.presentation.components.AppBar
import dev.alfin.airmaps.modules.presentation.components.CircleText
import dev.alfin.airmaps.modules.presentation.components.LoadingView
import dev.alfin.airmaps.modules.presentation.navigation.NavigationDestination
import dev.alfin.airmaps.modules.presentation.pages.detail_location.components.AirQualityBottomSheet
import org.koin.androidx.compose.koinViewModel

object DetailLocationDestination : NavigationDestination {
    override val route = "detail_location"
    override val titleRes = R.string.detail_location

    const val idArg = "latitude"
    val routeWithArgs = "$route/{$idArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLocationPage(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    viewModel: DetailLocationViewModel = koinViewModel()
) {
    val mapsCameraZoom = remember { 13f }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            viewModel.state.location.coordinate.asLatLng(),
            mapsCameraZoom
        )
    }

    LaunchedEffect(viewModel.state.location.coordinate) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            viewModel.state.location.coordinate.asLatLng(),
            mapsCameraZoom
        )
    }

    val context = LocalContext.current
    val isDark = isSystemInDarkTheme()

    // Air Quality Bottom Sheet Animation
    val airQualitySheetOffsetY = remember { Animatable(0f) }
    LaunchedEffect(viewModel.state.isShowAirQualitySheet) {
        val y = if (viewModel.state.isShowAirQualitySheet) 0f else 400f

        airQualitySheetOffsetY.animateTo(
            targetValue = y,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = modifier,
            topBar = {
                AppBar(
                    title = viewModel.state.location.name,
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
                    cameraPositionState = cameraPositionState
                ) {

                    // Handle Dark Mode
                    MapEffect(isSystemInDarkTheme()) { map ->
                        val style = MapStyleOptions.loadRawResourceStyle(
                            context,
                            if (isDark) R.raw.dark_map else R.raw.light_map
                        )
                        map.setMapStyle(style)
                    }

                    for (airQuality in viewModel.state.airQualities) {
                        val isSelected =
                            viewModel.state.selectedAirQuality == airQuality && viewModel.state.isShowAirQualitySheet

                        MarkerComposable(
                            keys = arrayOf("${airQuality.coordinate}${viewModel.state.selectedAirQuality?.coordinate}${viewModel.state.isShowAirQualitySheet}"),
                            state = MarkerState(position = airQuality.coordinate.asLatLng()),
                            anchor = Offset(0.5f, 0.5f),
                            onClick = {
                                val newState = viewModel.state.copy(
                                    selectedAirQuality = airQuality,
                                    isShowAirQualitySheet = true
                                )


                                if (
                                    viewModel.state.selectedAirQuality == airQuality && viewModel.state.isShowAirQualitySheet
                                ) {
                                    viewModel.setState {
                                        it.copy(
                                            isShowAirQualitySheet = false
                                        )
                                    }
                                } else {
                                    viewModel.setState { newState }
                                }

                                return@MarkerComposable true
                            }
                        ) {
                            CircleText(
                                text = airQuality.value.toString(),
                                color = airQuality.color,
                                borderWidth = if (isSelected) 48 else 0
                            )
                        }
                    }
                }


                if (viewModel.state.selectedAirQuality != null) {
                    AirQualityBottomSheet(
                        modifier = Modifier
                            .offset(
                                y = airQualitySheetOffsetY.value.dp
                            ),
                        airQuality = viewModel.state.selectedAirQuality!!,
                        onClickDownIcon = {
                            viewModel.setState {
                                it.copy(
                                    isShowAirQualitySheet = false
                                )
                            }
                        }
                    )
                }
            }
        }

        if (viewModel.state.isLoading) {
            LoadingView()
        }
    }
}

