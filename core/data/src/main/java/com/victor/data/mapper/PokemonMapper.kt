package com.victor.data.mapper

import com.victor.model.Ability
import com.victor.model.Pokemon
import com.victor.model.Stat
import com.victor.network.response.AbilityResponse
import com.victor.network.response.PokemonResponse
import com.victor.network.response.StatResponse

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