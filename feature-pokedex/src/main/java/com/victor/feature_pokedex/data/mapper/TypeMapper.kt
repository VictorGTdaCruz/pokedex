package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.DamageRelationsResponse
import com.victor.feature_pokedex.data.model.TypeResponse
import com.victor.feature_pokedex.domain.model.DamageRelations
import com.victor.feature_pokedex.domain.model.Type

internal fun TypeResponse.toDomain() =
    Type(
        id = id ?: 0,
        name = name.orEmpty(),
        damageRelations = damageRelations.toDomain(),
        pokemonList = pokemon?.map { it.pokemon }.toPokemonDomain(),
    )

private fun DamageRelationsResponse?.toDomain() = DamageRelations(
    doubleDamageFrom = this?.doubleDamageFrom?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    doubleDamageTo = this?.doubleDamageTo?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    halfDamageFrom = this?.halfDamageFrom?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    halfDamageTo = this?.halfDamageTo?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    noDamageFrom = this?.noDamageFrom?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    noDamageTo = this?.noDamageTo?.map { it.toTypeSimpleDomain() } ?: emptyList(),
)