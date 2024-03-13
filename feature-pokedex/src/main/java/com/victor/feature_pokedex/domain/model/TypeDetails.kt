package com.victor.feature_pokedex.domain.model

data class TypeDetails(
    val id: Long,
    val name: String,
    val pokemonList: List<PokemonSimple>,
)

data class PokemonSimple(
    val id: Long,
    val name: String,
)