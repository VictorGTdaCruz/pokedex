package com.victor.feature_pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Long? = null,
    val name: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val types: List<PokemonTypeWithSlotResponse>? = null,
    val stats: List<PokemonStatsResponse>? = null,
    val sprites: PokemonSpriteResponse? = null,
    val abilities: List<PokemonAbilityResponse>? = null,
    @SerializedName("base_experience") val baseXp: Int? = null,
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

data class PokemonStatsResponse(
    val base_stat: Int? = null,
    val effort: Int? = null,
    val stat: NameAndUrlResponse? = null
)

data class PokemonAbilityResponse(
    val ability: NameAndUrlResponse? = null,
    @SerializedName("is_hidden") val isHidden: Boolean? = null
)