package com.victor.pokedex.domain.model

data class PokemonDetails(
    val id: Long,
    val name: String,
    val types: List<PokemonTypeWithSlot>,
    val sprites: PokemonSprite
)

data class PokemonTypeWithSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonSprite(
    val frontDefault: String,
    val otherFrontDefault: String
)