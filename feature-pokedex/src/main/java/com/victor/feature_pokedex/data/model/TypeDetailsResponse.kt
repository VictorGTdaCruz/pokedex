package com.victor.feature_pokedex.data.model

data class TypeDetailsResponse(
    val id: Long? = null,
    val name: String? = null,
    val pokemon: List<PokemonResponse>? = null,
)

data class PokemonResponse(
    val pokemon: NameAndUrlResponse? = null,
    val slot: Int? = null
)