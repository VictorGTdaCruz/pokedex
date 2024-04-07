package com.victor.data.mapper

import com.victor.model.DamageRelations
import com.victor.model.Type
import com.victor.network.response.DamageRelationsResponse
import com.victor.network.response.TypeResponse

internal fun TypeResponse.toDomain(validPokemonRange: IntRange) =
    Type(
        id = id ?: 0,
        name = name.orEmpty(),
        damageRelations = damageRelations.toDomain(),
        pokemonList = pokemon?.map { it.pokemon }
            .toPokemonDomain()
            .filter { it.id in validPokemonRange },
    )

private fun DamageRelationsResponse?.toDomain() = DamageRelations(
    doubleDamageFrom = this?.doubleDamageFrom?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    doubleDamageTo = this?.doubleDamageTo?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    halfDamageFrom = this?.halfDamageFrom?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    halfDamageTo = this?.halfDamageTo?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    noDamageFrom = this?.noDamageFrom?.map { it.toTypeSimpleDomain() } ?: emptyList(),
    noDamageTo = this?.noDamageTo?.map { it.toTypeSimpleDomain() } ?: emptyList(),
)