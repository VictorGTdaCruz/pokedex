package com.victor.feature_pokedex.domain.model

data class PokemonGeneration(
    val id: Long,
    val pokemonList: List<PokemonSimple>
)