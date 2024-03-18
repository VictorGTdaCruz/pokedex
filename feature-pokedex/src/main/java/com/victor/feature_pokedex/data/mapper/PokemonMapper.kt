package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.AbilityResponse
import com.victor.feature_pokedex.data.model.PokemonResponse
import com.victor.feature_pokedex.data.model.PokemonSimpleResponse
import com.victor.feature_pokedex.data.model.StatResponse
import com.victor.feature_pokedex.domain.model.Ability
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.Stat

internal fun PokemonResponse.toDomain() =
    Pokemon(
        id = id ?: 0,
        name = name.orEmpty(),
        height = height ?: 0,
        weight = weight ?: 0,
        typeList = types?.map { it.type.toTypeSimpleDomain() } ?: emptyList(),
        statList = stats?.map { it.toDomain() } ?: emptyList(),
        sprite = sprites?.other?.officialArtwork?.frontDefault.orEmpty(),
        abilityList = abilities?.map { it.toDomain() } ?: emptyList(),
        baseXp = baseXp ?: 0,
    )

internal fun StatResponse?.toDomain() =
    Stat(
        name = this?.stat?.name.orEmpty(),
        baseStat = this?.baseStat ?: 0,
        effort = this?.effort ?: 0
    )

internal fun AbilityResponse?.toDomain() =
    Ability(
        name = this?.ability?.name.orEmpty(),
        isHidden = this?.isHidden ?: false
    )