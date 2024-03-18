package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.PokemonAbilityResponse
import com.victor.feature_pokedex.data.model.PokemonResponse
import com.victor.feature_pokedex.data.model.PokemonStatsResponse
import com.victor.feature_pokedex.domain.model.Ability
import com.victor.feature_pokedex.domain.model.Pokemon
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

internal fun PokemonStatsResponse?.toDomain() =
    Stat(
        name = this?.stat?.name.orEmpty(),
        baseStat = this?.base_stat ?: 0,
        effort = this?.effort ?: 0
    )

internal fun PokemonAbilityResponse?.toDomain() =
    Ability(
        name = this?.ability?.name.orEmpty(),
        isHidden = this?.isHidden ?: false
    )