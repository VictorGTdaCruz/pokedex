package com.victor.feature_pokedex.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val typeList: List<TypeSimple>,
    val statList: List<Stat>,
    val sprite: String,
    val abilityList: List<Ability>,
    val baseXp: Int,
)

data class Stat(
    val name: String,
    val baseStat: Int,
    val effort: Int
)

data class Ability(
    val name: String,
    val isHidden: Boolean
)

data class PokemonSimple(
    val id: Long,
    val name: String,
)