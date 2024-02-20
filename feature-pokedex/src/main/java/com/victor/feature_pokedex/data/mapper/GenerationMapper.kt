package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.GenerationResponse
import com.victor.feature_pokedex.domain.model.PokemonGeneration

internal fun GenerationResponse.toDomain() =
    PokemonGeneration(
        id = id ?: 0,
        pokemonList = pokemonList.toPokemonDomain(),
    )