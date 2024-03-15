package com.victor.feature_pokedex.data.model

import com.google.gson.annotations.SerializedName

data class EvolutionsResponse(
    val id: Long? = null,
    val chain: EvolutionsChainResponse? = null,
)

data class EvolutionsChainResponse(
    @SerializedName("evolves_to") val evolvesTo: List<EvolutionsChainResponse>? = null,
    @SerializedName("evolution_details") val evolutionDetails: List<EvolutionsDetailsResponse>? = null,
    @SerializedName("is_baby") val isBaby: Boolean? = null,
    val species: NameAndUrlResponse? = null
)

data class EvolutionsDetailsResponse(
    val trigger: NameAndUrlResponse? = null,
    @SerializedName("min_level") val minLevel: Int? = null,
)