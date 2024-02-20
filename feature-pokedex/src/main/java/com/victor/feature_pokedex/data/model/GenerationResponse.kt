package com.victor.feature_pokedex.data.model

import com.google.gson.annotations.SerializedName

data class GenerationResponse(
    val id: Long? = null,
    @SerializedName("pokemon_species") val pokemonList: List<NameAndUrlResponse>? = null
)