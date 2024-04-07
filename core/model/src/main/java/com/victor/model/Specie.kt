package com.victor.model

data class Specie(
    val id: Int,
    val captureRate: Int,
    val growthRate: String,
    val flavorText: String,
    val genera: String,
    val genderRate: Int,
    val eggGroups: List<String>,
    val hatchCounter: Int,
    val evolutionChainId: Int
)