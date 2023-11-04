package dev.alfin.airmaps.modules.presentation.pages.search_location


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.modules.domain.entities.Location
import dev.alfin.airmaps.modules.presentation.components.AppBar
import dev.alfin.airmaps.modules.presentation.components.EmptyView
import dev.alfin.airmaps.modules.presentation.components.GradientBackground
import dev.alfin.airmaps.modules.presentation.components.SearchTextField
import dev.alfin.airmaps.modules.presentation.navigation.NavigationDestination
import dev.alfin.airmaps.modules.presentation.pages.search_location.components.SearchLocationCard
import dev.alfin.airmaps.modules.presentation.theme.AppTheme
import org.koin.androidx.compose.koinViewModel


object SearchLocationDestination : NavigationDestination {
    override val route = "search_location"
    override val titleRes = R.string.search_location
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationPage(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onSelectLocation: (Location) -> Unit = {},
    viewModel: SearchLocationViewModel = koinViewModel()
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                canNavigateBack = true,
                onTapNavigateBack = navigateBack,
                content = {
                    SearchTextField(
                        modifier = Modifier
                            .focusRequester(focusRequester),
                        leadingIcon = {
                            Image(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = stringResource(R.string.button_icon),
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.onBackground.copy(
                                        alpha = 0.5f
                                    )
                                )
                            )
                        },
                        placeholder = stringResource(R.string.enter_location_name),
                        text = viewModel.state.keyword,
                        onChange = {
                            viewModel.setKeyword(it)
                            viewModel.searchLocation(keyword = it)
                        },
                    )
                }
            )
        }
    ) { innerPadding ->
        GradientBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (viewModel.state.keyword.isNotEmpty() && viewModel.state.locationList.isEmpty()) {
                    EmptyView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        icon = painterResource(R.drawable.ic_location_slash),
                        title = stringResource(R.string.no_location_data),
                        description = stringResource(R.string.no_location_data_description)
                    )
                } else {
                    LazyColumn(
                        modifier = modifier,
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = viewModel.state.locationList, key = { it.id }) { location ->
                            SearchLocationCard(
                                location = location,
                                onClick = {
                                    onSelectLocation(location)
                                }
                            )
                        }
                    }

                }

            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SearchLocationPagePreview() {
    AppTheme {
        SearchLocationPage()
    }
}
