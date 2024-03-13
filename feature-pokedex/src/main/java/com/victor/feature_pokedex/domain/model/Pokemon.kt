package com.victor.feature_pokedex.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeWithSlot>,
    val stats: List<PokemonStat>,
    val sprites: PokemonSprite,
    val abilities: List<PokemonAbility>,
    val baseXp: Int,
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

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean
)