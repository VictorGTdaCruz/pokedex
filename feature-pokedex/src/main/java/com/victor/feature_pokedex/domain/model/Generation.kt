package com.victor.feature_pokedex.domain.model

data class Generation(
    val id: Long,
    val pokemonList: List<PokemonSimple>
)