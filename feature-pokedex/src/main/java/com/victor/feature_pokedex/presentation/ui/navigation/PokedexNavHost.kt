package com.victor.feature_pokedex.presentation.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.home.HomeScreenBody
import com.victor.feature_pokedex.presentation.ui.pokemons.PokemonsScreenBody
import com.victor.feature_pokedex.presentation.ui.types.PokemonTypesScreenBody

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
internal fun PokedexNavHost(
    navController: NavHostController,
    viewModel: PokedexViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name,
        modifier = modifier
    ) {
        composable(Screens.HomeScreen.name) {
            HomeScreenBody(viewModel = viewModel)
        }

        composable(Screens.PokemonTypesScreen.name) {
            PokemonTypesScreenBody(
                viewModel = viewModel,
                onTypeClick = {
                    val route = "${Screens.PokemonListByTypeScreen.name}/${it.name}&${it.id}"
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = "${Screens.PokemonListByTypeScreen.name}/{typeName}&{typeId}",
            arguments = listOf(
                navArgument("typeName") { type = NavType.StringType },
                navArgument("typeId") { type = NavType.LongType },
            )
        ) {
            val typeName = it.arguments?.getString("typeName") ?: ""
            val typeId = it.arguments?.getLong("typeId") ?: 0

            PokemonsScreenBody(
                viewModel = viewModel,
                pokemonTypeId = typeId,
                pokemonTypeName = typeName
            )
        }
    }
}