package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.NameAndUrlResponse
import com.victor.feature_pokedex.data.model.PagedResponse
import com.victor.feature_pokedex.domain.model.PokemonSimple

internal fun PagedResponse<NameAndUrlResponse>.toPokemonListDomain() =
    results?.toPokemonDomain()
        ?: emptyList()

internal fun List<NameAndUrlResponse?>?.toPokemonDomain() = this?.map {
    PokemonSimple(
        id = IdMapper.mapIdFromUrl(it?.url),
        name = it?.name.orEmpty(),
    )
} ?: emptyList()