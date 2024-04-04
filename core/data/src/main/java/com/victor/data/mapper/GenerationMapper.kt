package com.victor.data.mapper

import com.victor.model.Generation
import com.victor.network.response.GenerationResponse

internal fun GenerationResponse.toDomain() =
    Generation(
        id = id ?: 0,
        pokemonList = pokemonList.toPokemonDomain(),
    )