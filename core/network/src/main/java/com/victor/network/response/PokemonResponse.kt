package com.victor.network.response

import com.google.gson.annotations.SerializedName

data class PokemonSimpleResponse(
    val pokemon: NameAndUrlResponse? = null,
)

data class PokemonResponse(
    val id: Int? = null,
    val name: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val types: List<TypeSimpleResponse>? = null,
    val stats: List<StatResponse>? = null,
    val sprites: SpriteResponse? = null,
    val abilities: List<AbilityResponse>? = null,
    @SerializedName("base_experience") val baseXp: Int? = null,
)

data class SpriteResponse(
    @SerializedName("front_default") val frontDefault: String? = null,
    val other: OtherSpritesResponse? = null
)

data class OtherSpritesResponse(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkSpriteResponse? = null
)

data class OfficialArtworkSpriteResponse(
    @SerializedName("front_default") val frontDefault: String? = null
)

data class StatResponse(
    @SerializedName("base_stat") val baseStat: Int? = null,
    val effort: Int? = null,
    val stat: NameAndUrlResponse? = null
)

data class AbilityResponse(
    val ability: NameAndUrlResponse? = null,
    @SerializedName("is_hidden") val isHidden: Boolean? = null
)