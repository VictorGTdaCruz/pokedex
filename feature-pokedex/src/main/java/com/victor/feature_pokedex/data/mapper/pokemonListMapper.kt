package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.NameAndUrlResponse
import com.victor.feature_pokedex.data.model.PagedResponse
import com.victor.feature_pokedex.domain.model.Pokemon

internal fun PagedResponse<NameAndUrlResponse>.toPokemonListDomain() =
    results?.toPokemonDomain()
        ?: emptyList()

internal fun List<NameAndUrlResponse>?.toPokemonDomain() = this?.map {
    Pokemon(
        id = IdMapper.fromPokemonUrl(it.url),
        name = it.name.orEmpty(),
        slot = 0
    )
} ?: emptyList()
