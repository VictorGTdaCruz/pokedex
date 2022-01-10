package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.PokemonDetailsResponse
import com.victor.pokedex.data.model.PokemonSpriteResponse
import com.victor.pokedex.data.model.PokemonTypeWithSlotResponse
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonSprite
import com.victor.pokedex.domain.model.PokemonTypeWithSlot

internal fun PokemonDetailsResponse.toDomain() =
    PokemonDetails(
        id = id ?: 0,
        name = name.orEmpty(),
        types = types?.map { it.toDomain() } ?: emptyList(),
        sprites = sprites.toDomain()
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