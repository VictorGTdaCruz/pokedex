package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.NameAndUrlResponse
import com.victor.feature_pokedex.data.model.PagedResponse
import com.victor.feature_pokedex.domain.model.TypeSimple

internal fun PagedResponse<NameAndUrlResponse>.toPokemonTypesDomain() =
    results?.toTypeSimpleDomain()
        ?: emptyList()

internal fun List<NameAndUrlResponse>?.toTypeSimpleDomain() =
    this?.map { it.toTypeSimpleDomain() }
        ?: emptyList()

internal fun NameAndUrlResponse?.toTypeSimpleDomain() =
    TypeSimple(
        name = this?.name.orEmpty(),
        id = IdMapper.mapIdFromUrl(this?.url)
    )