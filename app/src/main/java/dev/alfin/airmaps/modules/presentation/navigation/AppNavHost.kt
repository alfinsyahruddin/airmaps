package dev.alfin.airmaps.modules.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.alfin.airmaps.modules.presentation.pages.add_location.AddLocationDestination
import dev.alfin.airmaps.modules.presentation.pages.add_location.AddLocationPage
import dev.alfin.airmaps.modules.presentation.pages.detail_location.DetailLocationDestination
import dev.alfin.airmaps.modules.presentation.pages.detail_location.DetailLocationPage
import dev.alfin.airmaps.modules.presentation.pages.edit_location.EditLocationDestination
import dev.alfin.airmaps.modules.presentation.pages.edit_location.EditLocationPage
import dev.alfin.airmaps.modules.presentation.pages.home.HomeDestination
import dev.alfin.airmaps.modules.presentation.pages.home.HomePage
import dev.alfin.airmaps.modules.presentation.pages.pick_location.PickLocationDestination
import dev.alfin.airmaps.modules.presentation.pages.pick_location.PickLocationPage
import dev.alfin.airmaps.modules.presentation.pages.search_location.SearchLocationDestination
import dev.alfin.airmaps.modules.presentation.pages.search_location.SearchLocationPage


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeDestination.route,
    ) {
        // MARK: - Home
        composable(route = HomeDestination.route) {
            HomePage(
                navigateToAddLocation = { navController.navigate(AddLocationDestination.route) },
                navigateToEditLocation = { navController.navigate("${EditLocationDestination.route}/$it") },
                navigateToDetailLocation = { navController.navigate("${DetailLocationDestination.route}/$it") },
            )
        }

        // MARK: - Add Location
        composable(route = AddLocationDestination.route) { entry ->
            AddLocationPage(
                locationName = entry.savedStateHandle.get<String>("locationName"),
                latitude = entry.savedStateHandle.get<Double>("latitude"),
                longitude = entry.savedStateHandle.get<Double>("longitude"),
                navigateBack = { navController.popBackStack() },
                navigateToSearchLocation = {
                    navController.navigate(SearchLocationDestination.route)
                },
                navigateToPickLocation = {
                    navController.navigate("${PickLocationDestination.route}/${it.latitude}/${it.longitude}")
                },
            )
        }

        // MARK: - Edit Location
        composable(
            route = EditLocationDestination.routeWithArgs,
            arguments = listOf(
                navArgument(EditLocationDestination.idArg) {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            EditLocationPage(
                locationName = entry.savedStateHandle.get<String>("locationName"),
                latitude = entry.savedStateHandle.get<Double>("latitude"),
                longitude = entry.savedStateHandle.get<Double>("longitude"),
                navigateBack = { navController.popBackStack() },
                navigateToSearchLocation = {
                    navController.navigate(SearchLocationDestination.route)
                },
                navigateToPickLocation = {
                    navController.navigate("${PickLocationDestination.route}/${it.latitude}/${it.longitude}")
                },
            )
        }


        // MARK: - Detail Location
        composable(
            route = DetailLocationDestination.routeWithArgs,
            arguments = listOf(
                navArgument(DetailLocationDestination.idArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailLocationPage(
                navigateBack = { navController.popBackStack() },
            )
        }


        // MARK: - Search Location
        composable(
            route = SearchLocationDestination.route,
        ) {
            SearchLocationPage(
                navigateBack = { navController.popBackStack() },
                onSelectLocation = { location ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "locationName",
                        location.name
                    )
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "latitude",
                        location.coordinate.latitude
                    )
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "longitude",
                        location.coordinate.longitude
                    )
                    navController.popBackStack()
                }
            )
        }

        // MARK: - Pick Location
        composable(
            route = PickLocationDestination.routeWithArgs,
            arguments = listOf(
                navArgument(PickLocationDestination.latitudeArg) {
                    type = NavType.FloatType
                },
                navArgument(PickLocationDestination.longitudeArg) {
                    type = NavType.FloatType
                },
            )
        ) {
            PickLocationPage(
                navigateBack = { navController.popBackStack() },
                onPickLocation = { coordinate ->
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "locationName",
                        null
                    )
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "latitude",
                        coordinate.latitude
                    )
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "longitude",
                        coordinate.longitude
                    )
                    navController.popBackStack()
                }
            )
        }


    }
}
