package com.victor.feature_pokedex.presentation.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.home.HomeScreenBody

@ExperimentalFoundationApi
@Composable
internal fun PokedexNavHost(
    navController: NavHostController,
    viewModel: PokedexViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name,
        modifier = modifier
    ) {
        composable(Screens.HomeScreen.name) {
            HomeScreenBody(viewModel = viewModel)
        }
    }
}