package com.victor.feature_pokedex.data.model

data class TypeDetailsResponse(
    val id: Long? = null,
    val name: String? = null,
    val pokemon: List<PokemonSimpleResponse>? = null,
)

data class PokemonSimpleResponse(
    val pokemon: NameAndUrlResponse? = null,
    val slot: Int? = null
)