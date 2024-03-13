package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.PokemonSimpleResponse
import com.victor.feature_pokedex.data.model.TypeDetailsResponse
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.TypeDetails

internal fun TypeDetailsResponse.toDomain() =
    TypeDetails(
        id = id ?: 0,
        name = name.orEmpty(),
        pokemonList = pokemon.toPokemonDomain(),
    )

private fun List<PokemonSimpleResponse>?.toPokemonDomain() = this?.map {
    PokemonSimple(
        id = IdMapper.mapIdFromUrl(it.pokemon?.url),
        name = it.pokemon?.name.orEmpty(),
    )
} ?: emptyList()