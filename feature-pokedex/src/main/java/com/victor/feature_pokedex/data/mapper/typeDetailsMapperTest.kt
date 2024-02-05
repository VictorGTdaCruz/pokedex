package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.PokemonResponse
import com.victor.feature_pokedex.data.model.TypeDetailsResponse
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.TypeDetails

internal fun TypeDetailsResponse.toDomain() =
    TypeDetails(
        id = id ?: 0,
        name = name.orEmpty(),
        pokemons = pokemon.toPokemonDomain(),
    )

private fun List<PokemonResponse>?.toPokemonDomain() = this?.map {
    Pokemon(
        id = IdMapper.fromPokemonUrl(it.pokemon?.url),
        name = it.pokemon?.name.orEmpty(),
        slot = it.slot ?: 0
    )
} ?: emptyList()