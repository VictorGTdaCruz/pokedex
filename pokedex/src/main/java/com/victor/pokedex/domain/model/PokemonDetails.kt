package com.victor.pokedex.domain.model

import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val id: Long,
    val name: String,
    val types: List<PokemonTypeWithSlot>,
    val sprites: PokemonSprite
)

data class PokemonTypeWithSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonSprite(
    @SerializedName("front_default") val frontDefault: String
)