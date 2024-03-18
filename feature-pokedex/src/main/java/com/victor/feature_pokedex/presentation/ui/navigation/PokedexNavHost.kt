package com.victor.feature_pokedex.presentation.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.details.DetailsScreenBody
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
            HomeScreenBody(viewModel = viewModel, onPokemonClick = {
                val route = "${Screens.DetailsScreen.name}/$it"
                navController.navigate(route)
            })
        }
        composable(
            route = "${Screens.DetailsScreen.name}/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.LongType }
            )
        ) {
            val pokemonId = it.arguments?.getInt("pokemonId") ?: 0
            DetailsScreenBody(
                navController = navController,
                viewModel = viewModel,
                pokemonId = pokemonId,
                onPokemonClick = { id ->
                    if (id != pokemonId) {
                        val route = "${Screens.DetailsScreen.name}/$id"
                        navController.navigate(route)
                    }
                }
            )
        }
    }
}