package com.victor.pokedex.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val id: Long,
    val name: String,
    val types: List<Tste>,
    val sprites: PokemonSprite
)

data class Tste(
    val slot: Int,
    val type: Tstet
)

data class PokemonSprite(
    @SerializedName("front_default") val frontDefault: String
)

data class Tstet(
    val name: String,
    val url: String
)