package com.victor.model

data class Evolution(
    val id: Long? = null,
    val chain: EvolutionChain? = null,
)

data class EvolutionChain(
    val specieId: Int? = null,
    val evolvesTo: List<EvolutionChain>? = null,
    val evolutionDetails: List<EvolutionDetails>? = null,
    val isBaby: Boolean? = null,
)

data class EvolutionDetails(
    val trigger: String? = null,
    val minLevel: Int? = null,
)
