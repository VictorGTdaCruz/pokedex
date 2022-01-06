package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PagedResponse
import com.victor.pokedex.domain.model.PokemonTypeSimplified

internal fun PagedResponse<NameAndUrlResponse>.toPokemonTypesDomain() =
    results?.toPokemonTypeSimplifiedDomain()
        ?: emptyList()

internal fun List<NameAndUrlResponse>?.toPokemonTypeSimplifiedDomain() =
    this?.map {
        PokemonTypeSimplified(
            name = it.name.orEmpty(),
            id = it.url.mapIdFromUrl()
        )
    } ?: emptyList()

private fun String?.mapIdFromUrl() =
    this?.substringAfter("type/")
        ?.substringBefore("/")
        ?.toLongOrNull()
        ?: 0