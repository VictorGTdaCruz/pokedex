package com.victor.feature_pokedex.domain.model

data class Generation(
    val id: Int,
    val pokemonList: List<PokemonSimple>
)