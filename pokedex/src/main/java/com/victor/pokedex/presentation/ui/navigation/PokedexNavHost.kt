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
import com.victor.pokedex.presentation.ui.details.DetailsScreenBody
import com.victor.pokedex.presentation.ui.pokemons.PokemonsScreenBody
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
                pokemonTypeName = typeName,
                onPokemonClick = { pokemon ->
                    val route = "${Screens.PokemonDetailsScreen.name}/${pokemon.id}&${pokemon.name}"
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = "${Screens.PokemonDetailsScreen.name}/{pokemonId}&{pokemonName}",
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.LongType },
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) {
            val pokemonId = it.arguments?.getLong("pokemonId") ?: 0
            val pokemonName = it.arguments?.getString("pokemonName") ?: ""
            DetailsScreenBody(
                viewModel = viewModel,
                pokemonId = pokemonId,
                pokemonName = pokemonName
            )
        }
    }
}