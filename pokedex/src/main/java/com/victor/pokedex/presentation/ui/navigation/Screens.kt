package com.victor.pokedex.presentation.ui.navigation

import com.victor.pokedex.R

enum class Screens(private val title: Int){
    PokemonTypesScreen(R.string.type_screen_title),
    PokemonListByTypeScreen(R.string.pokemons_screen_title),
    PokemonDetailsScreen(R.string.details_screen_title);

    companion object {
        fun getScreenTitle(route: String) =
            values().find { route.contains(it.name) }?.title ?: PokemonTypesScreen.title
    }
}