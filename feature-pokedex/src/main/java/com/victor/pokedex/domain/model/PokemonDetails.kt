package com.victor.pokedex.domain.model

data class PokemonDetails(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeWithSlot>,
    val stats: List<PokemonStat>,
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

data class PokemonStat(
    val name: String,
    val baseStat: Int
)