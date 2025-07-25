package com.example.swordhealthproject.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swordhealthproject.R
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModel

enum class Screens(val display: String, val showAppBar : Boolean) {
    CATBREEDS("Cat Breeds", false),
    FAVOURITES("Favourites", true),
    CATDETAILS("Cat Details", true);

    val route : String
        get() = this.toString()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), catBreedViewModel: CatBreedViewModel){

    val currentScreen by navController.currentBackStackEntryAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val configuration = LocalConfiguration.current


    Scaffold (
        snackbarHost = { SnackbarHost (hostState = snackbarHostState) },
        topBar = {
            if (currentScreen != null && Screens.valueOf(currentScreen!!.destination.route!!).showAppBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = when (currentScreen!!.destination.route) {
                                Screens.CATBREEDS.route -> stringResource(R.string.app_name)
                                Screens.FAVOURITES.route -> stringResource(R.string.app_name)
                                Screens.CATDETAILS.route -> stringResource(R.string.app_name)
                                else -> {
                                    stringResource(R.string.app_name)
                                }
                            },
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White
                    ),
                )
            }
        }, modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.CATBREEDS.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screens.CATBREEDS.route) {
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> LandscapeCatBreedsListScreen(navController, catBreedViewModel)
                    else -> CatBreedsListScreen(navController, catBreedViewModel)
                }
            }
            composable(Screens.FAVOURITES.route) {
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> LandscapeFavouritesScreen(navController, catBreedViewModel)
                    else -> FavouritesScreen(navController,catBreedViewModel)
                }
            }
            composable(Screens.CATDETAILS.route) {
                when (configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> LandscapeCatDetailsScreen(navController, catBreedViewModel)
                    else -> CatDetailsScreen(navController, catBreedViewModel)
                }
            }
        }
    }
}