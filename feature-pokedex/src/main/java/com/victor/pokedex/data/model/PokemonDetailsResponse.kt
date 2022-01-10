package com.victor.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    val id: Long? = null,
    val name: String? = null,
    val types: List<PokemonTypeWithSlotResponse>? = null,
    val sprites: PokemonSpriteResponse? = null
)

data class PokemonTypeWithSlotResponse(
    val slot: Int? = null,
    val type: NameAndUrlResponse? = null
)

data class PokemonSpriteResponse(
    @SerializedName("front_default") val frontDefault: String? = null,
    val other: PokemonOtherSpritesResponse? = null
)

data class PokemonOtherSpritesResponse(
    @SerializedName("official-artwork") val officialArtwork: PokemonOfficialArtworkSpriteResponse? = null
)

data class PokemonOfficialArtworkSpriteResponse(
    @SerializedName("front_default") val frontDefault: String? = null
)