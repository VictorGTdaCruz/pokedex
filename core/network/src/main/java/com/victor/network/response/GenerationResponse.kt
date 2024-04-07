package com.victor.network.response

import com.google.gson.annotations.SerializedName

data class GenerationResponse(
    val id: Int? = null,
    @SerializedName("pokemon_species") val pokemonList: List<NameAndUrlResponse>? = null
)