package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.PokemonAbilityResponse
import com.victor.feature_pokedex.data.model.PokemonResponse
import com.victor.feature_pokedex.data.model.PokemonSpriteResponse
import com.victor.feature_pokedex.data.model.PokemonStatsResponse
import com.victor.feature_pokedex.data.model.PokemonTypeWithSlotResponse
import com.victor.feature_pokedex.domain.model.PokemonAbility
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonSprite
import com.victor.feature_pokedex.domain.model.PokemonStat
import com.victor.feature_pokedex.domain.model.PokemonTypeWithSlot

internal fun PokemonResponse.toDomain() =
    Pokemon(
        id = id ?: 0,
        name = name.orEmpty(),
        height = height ?: 0,
        weight = weight ?: 0,
        types = types?.map { it.toDomain() } ?: emptyList(),
        stats = stats?.map { it.toDomain() } ?: emptyList(),
        sprites = sprites.toDomain(),
        abilities = abilities?.map { it.toDomain() } ?: emptyList(),
        baseXp = baseXp ?: 0,
    )

internal fun PokemonTypeWithSlotResponse.toDomain() =
    PokemonTypeWithSlot(
        slot = slot ?: 0,
        type = type.toPokemonTypeDomain()
    )

internal fun PokemonSpriteResponse?.toDomain() =
    PokemonSprite(
        frontDefault = this?.frontDefault.orEmpty(),
        otherFrontDefault = this?.other?.officialArtwork?.frontDefault.orEmpty()
    )

internal fun PokemonStatsResponse?.toDomain() =
    PokemonStat(
        name = this?.stat?.name.orEmpty(),
        baseStat = this?.base_stat ?: 0,
        effort = this?.effort ?: 0
    )

internal fun PokemonAbilityResponse?.toDomain() =
    PokemonAbility(
        name = this?.ability?.name.orEmpty(),
        isHidden = this?.isHidden ?: false
    )