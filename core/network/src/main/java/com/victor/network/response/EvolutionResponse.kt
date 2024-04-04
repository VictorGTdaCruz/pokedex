package com.victor.network.response

import com.google.gson.annotations.SerializedName

data class EvolutionResponse(
    val id: Long? = null,
    val chain: EvolutionChainResponse? = null,
)

data class EvolutionChainResponse(
    @SerializedName("evolves_to") val evolvesTo: List<EvolutionChainResponse>? = null,
    @SerializedName("evolution_details") val evolutionDetails: List<EvolutionDetailsResponse>? = null,
    @SerializedName("is_baby") val isBaby: Boolean? = null,
    val species: NameAndUrlResponse? = null
)

data class EvolutionDetailsResponse(
    val trigger: NameAndUrlResponse? = null,
    @SerializedName("min_level") val minLevel: Int? = null,
)