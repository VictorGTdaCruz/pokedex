package com.victor.feature_pokedex.domain.model

data class PokemonSpecies(
    val id: Long,
    val captureRate: Int,
    val growthRate: String,
    val flavorText: String,
    val genera: String,
    val genderRate: Int,
    val eggGroups: List<String>,
    val hatchCounter: Int,
    val evolutionChainId: Long
)