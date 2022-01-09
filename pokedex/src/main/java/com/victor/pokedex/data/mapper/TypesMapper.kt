package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PagedResponse
import com.victor.pokedex.domain.model.PokemonType

internal fun PagedResponse<NameAndUrlResponse>.toPokemonTypesDomain() =
    results?.toPokemonTypeDomain()
        ?: emptyList()

internal fun List<NameAndUrlResponse>?.toPokemonTypeDomain() =
    this?.map { it.toPokemonTypeDomain() }
        ?: emptyList()

internal fun NameAndUrlResponse?.toPokemonTypeDomain() =
    PokemonType(
        name = this?.name.orEmpty(),
        id = IdMapper.fromTypeUrl(this?.url)
    )