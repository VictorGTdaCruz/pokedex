package com.victor.feature_pokedex.data.model

import com.google.gson.annotations.SerializedName

data class SpecieResponse(
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("capture_rate") val captureRate: Int? = null,
    @SerializedName("growth_rate") val growthRate: NameAndUrlResponse? = null,
    @SerializedName("flavor_text_entries") val flavorTextList: List<FlavorTextResponse>? = null,
    val genera: List<GeneraResponse>? = null,
    @SerializedName("gender_rate") val genderRate: Int? = null,
    @SerializedName("egg_groups") val eggGroups: List<NameAndUrlResponse>? = null,
    @SerializedName("hatch_counter") val hatchCounter: Int? = null,
    @SerializedName("evolution_chain") val evolutionChain: NameAndUrlResponse? = null,
)

data class FlavorTextResponse(
    @SerializedName("flavor_text") val flavorText: String? = null,
    val version: NameAndUrlResponse? = null
)

data class GeneraResponse(
    val genus: String? = null,
    val language: NameAndUrlResponse? = null
)