package com.victor.feature_pokedex.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val typeList: List<PokemonTypeWithSlot>,
    val stats: List<PokemonStat>,
    val sprites: PokemonSprite,
    val abilities: List<PokemonAbility>,
    val baseXp: Int,
)

// TODO remove anything with slot
data class PokemonTypeWithSlot(
    val slot: Int,
    val type: TypeSimple
)

data class PokemonSprite(
    val frontDefault: String,
    val otherFrontDefault: String
)

data class PokemonStat(
    val name: String,
    val baseStat: Int,
    val effort: Int
)

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean
)