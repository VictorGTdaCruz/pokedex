package com.victor.feature_pokedex.domain.model

data class PokemonInformation(
    val id: Int,
    val name: String,
    val height: Float,
    val weight: Float,
    val typeList: List<TypeSimple>,
    val stats: List<Stat>,
    val sprite: String,
    val abilityList: List<Ability>,
    val baseXp: Int,
    val captureRate: Int,
    val captureProbability: Float,
    val growthRate: String,
    val flavorText: String,
    val genera: String,
    val maleRate: Double?,
    val femaleRate: Double?,
    val eggGroupList: List<String>,
    val hatchCounter: Int,
    val hatchSteps: Int,
    val typeDefenseList: List<TypeEffectiveness>,
    val weaknessList: List<TypeSimple>,
    val evolutionList: List<PokemonEvolution>,
)

data class TypeEffectiveness(
    val type: TypeSimple,
    val effectiveness: Double
)

data class PokemonEvolution(
    val from: List<Pokemon>?,
    val to: List<Pokemon>?,
    val trigger: String?,
    val minLevel: Int?,
)