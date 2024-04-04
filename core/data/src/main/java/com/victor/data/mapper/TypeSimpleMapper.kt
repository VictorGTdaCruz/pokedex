package com.victor.data.mapper

import com.victor.model.TypeSimple
import com.victor.network.response.NameAndUrlResponse
import com.victor.network.response.PagedResponse

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