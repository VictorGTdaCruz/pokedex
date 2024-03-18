package com.victor.feature_pokedex.domain.model

data class PokemonInformation(
    val id: Long,
    val name: String,
    val height: Float,
    val weight: Float,
    val types: List<PokemonTypeWithSlot>,
    val stats: List<PokemonStat>,
    val sprites: PokemonSprite,
    val abilities: List<PokemonAbility>,
    val baseXp: Int,
    val captureRate: Int,
    val captureProbability: Float,
    val growthRate: String,
    val flavorText: String,
    val genera: String,
    val maleRate: Double?,
    val femaleRate: Double?,
    val eggGroups: List<String>,
    val hatchCounter: Int,
    val hatchSteps: Int,
    val typeDefenses: List<TypeEffectiveness>,
    val weaknesses: List<PokemonType>,
    val evolutions: List<PokemonEvolution>,
)

data class TypeEffectiveness(
    val type: PokemonType,
    val effectiveness: Double
)

data class PokemonEvolution(
    val from: List<Pokemon>?,
    val to: List<Pokemon>?,
    val trigger: String?,
    val minLevel: Int?,
)