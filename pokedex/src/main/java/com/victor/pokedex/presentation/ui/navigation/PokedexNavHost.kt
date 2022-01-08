package com.victor.pokedex.presentation.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.types.PokemonTypesScreenBody

@ExperimentalFoundationApi
@Composable
internal fun PokedexNavHost(
    navController: NavHostController,
    viewModel: PokedexViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.PokemonTypesScreen.name,
        modifier = modifier
    ) {
        composable(Screens.PokemonTypesScreen.name) {
            PokemonTypesScreenBody(
                viewModel = viewModel,
                onTypeClick = {
//                    navController.navigate(Screens.PokemonListByTypeScreen.name)
                }
            )
        }

        composable(
            route = Screens.PokemonListByTypeScreen.name,
            arguments = listOf(navArgument("typeId") {
                type = NavType.LongType
            })
        ) {
            // TODO list pokemon by type screen
        }

        composable(Screens.PokemonDetailsScreen.name) {
            // TODO list pokemon details screen
        }
    }
}