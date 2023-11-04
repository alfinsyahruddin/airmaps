package dev.alfin.airmaps.modules.presentation.pages.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.alfin.airmaps.R
import dev.alfin.airmaps.core.helpers.mainThread
import dev.alfin.airmaps.modules.presentation.components.AppBar
import dev.alfin.airmaps.modules.presentation.components.EmptyView
import dev.alfin.airmaps.modules.presentation.components.GradientBackground
import dev.alfin.airmaps.modules.presentation.navigation.NavigationDestination
import dev.alfin.airmaps.modules.presentation.pages.home.components.LocationCard
import dev.alfin.airmaps.modules.presentation.theme.AppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.home
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navigateToAddLocation: () -> Unit = {},
    navigateToEditLocation: (Int) -> Unit = {},
    navigateToDetailLocation: (Int) -> Unit = {},
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                showLogo = true,
                canNavigateBack = false,
                endContent = {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = stringResource(R.string.data_by),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25F)
                        )

                        Text(
                            text = "Google Air Quality API",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25F)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .testTag("add-location-button"),
                shape = MaterialTheme.shapes.extraLarge,
                onClick = navigateToAddLocation
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_location)
                )
            }
        },
    ) { innerPadding ->
        GradientBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .testTag("content"),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (state.locationList.isEmpty()) {
                    EmptyView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        icon = painterResource(R.drawable.ic_location_x),
                        title = stringResource(R.string.empty_location),
                        description = stringResource(R.string.empty_location_description)
                    )
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(items = state.locationList, key = { it.id }) { location ->
                            LocationCard(
                                location = location,
                                onClick = {
                                    navigateToDetailLocation(location.id)
                                },
                                onClickEdit = {
                                    navigateToEditLocation(location.id)
                                },
                                onClickDelete = {
                                    coroutineScope.launch {
                                        viewModel.deleteLocation(location)

                                        mainThread {
                                            Toast.makeText(
                                                context,
                                                R.string.delete_location_success,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
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
fun HomePagePreview() {
    AppTheme {
        HomePage()
    }
}
