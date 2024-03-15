package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.DamageRelationsResponse
import com.victor.feature_pokedex.data.model.PokemonSimpleResponse
import com.victor.feature_pokedex.data.model.TypeDetailsResponse
import com.victor.feature_pokedex.domain.model.DamageRelations
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.TypeDetails

internal fun TypeDetailsResponse.toDomain() =
    TypeDetails(
        id = id ?: 0,
        name = name.orEmpty(),
        damageRelations = damageRelations.toDomain(),
        pokemonList = pokemon.toPokemonDomain(),
    )

private fun List<PokemonSimpleResponse>?.toPokemonDomain() = this?.map {
    PokemonSimple(
        id = IdMapper.mapIdFromUrl(it.pokemon?.url),
        name = it.pokemon?.name.orEmpty(),
    )
} ?: emptyList()

private fun DamageRelationsResponse?.toDomain() = DamageRelations(
    doubleDamageFrom = this?.doubleDamageFrom?.map { it.toPokemonTypeDomain() } ?: emptyList(),
    doubleDamageTo = this?.doubleDamageTo?.map { it.toPokemonTypeDomain() } ?: emptyList(),
    halfDamageFrom = this?.halfDamageFrom?.map { it.toPokemonTypeDomain() } ?: emptyList(),
    halfDamageTo = this?.halfDamageTo?.map { it.toPokemonTypeDomain() } ?: emptyList(),
    noDamageFrom = this?.noDamageFrom?.map { it.toPokemonTypeDomain() } ?: emptyList(),
    noDamageTo = this?.noDamageTo?.map { it.toPokemonTypeDomain() } ?: emptyList(),
)