package com.victor.data.mapper

import com.victor.model.PokemonSimple
import com.victor.network.response.NameAndUrlResponse
import com.victor.network.response.PagedResponse

internal fun PagedResponse<NameAndUrlResponse>.toPokemonListDomain() =
    results?.toPokemonDomain()
        ?: emptyList()

internal fun List<NameAndUrlResponse?>?.toPokemonDomain() = this?.map {
    PokemonSimple(
        id = IdMapper.mapIdFromUrl(it?.url),
        name = it?.name.orEmpty(),
    )
} ?: emptyList()