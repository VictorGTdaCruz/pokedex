package com.victor.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class TypeDetailsResponse(
    val id: Long? = null,
    val name: String? = null,
    @SerializedName("damage_relations") val damageRelations: DamageRelationsResponse? = null,
    @SerializedName("past_damage_relations") val pastDamageRelations: List<PastDamageRelationsResponse>? = null,
    @SerializedName("game_indices") val gameIndices: List<GameIndexResponse>? = null,
    val generation: NameAndUrlResponse? = null,
    @SerializedName("move_damage_class") val moveDamageClass: NameAndUrlResponse? = null,
    val names: List<PokemonNameResponse>? = null,
    val pokemon: List<PokemonResponse>? = null,
    val moves: List<NameAndUrlResponse>? = null
)

data class DamageRelationsResponse(
    @SerializedName("no_damage_to") val noDamageTo: List<NameAndUrlResponse>? = null,
    @SerializedName("half_damage_to") val halfDamageTo: List<NameAndUrlResponse>? = null,
    @SerializedName("double_damage_to") val doubleDamageTo: List<NameAndUrlResponse>? = null,
    @SerializedName("no_damage_from") val noDamageFrom: List<NameAndUrlResponse>? = null,
    @SerializedName("half_damage_from") val halfDamageFrom: List<NameAndUrlResponse>? = null,
    @SerializedName("double_damage_from") val doubleDamageFrom: List<NameAndUrlResponse>? = null
)

data class PastDamageRelationsResponse(
    @SerializedName("damage_relations") val damageRelations: DamageRelationsResponse? = null,
    val generation: NameAndUrlResponse? = null
)

data class GameIndexResponse(
    val index: Int? = null,
    val url: String? = null
)

data class PokemonNameResponse(
    val name: String? = null,
    val language: NameAndUrlResponse? = null
)

data class PokemonResponse(
    val pokemon: NameAndUrlResponse? = null,
    val slot: Int? = null
)