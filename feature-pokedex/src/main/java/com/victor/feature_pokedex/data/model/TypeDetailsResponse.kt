package com.victor.feature_pokedex.data.model

import com.google.gson.annotations.SerializedName

data class TypeDetailsResponse(
    val id: Long? = null,
    val name: String? = null,
    @SerializedName("damage_relations") val damageRelations: DamageRelationsResponse? = null,
    val pokemon: List<PokemonSimpleResponse>? = null,
)

data class PokemonSimpleResponse(
    val pokemon: NameAndUrlResponse? = null,
    val slot: Int? = null
)

data class DamageRelationsResponse(
    @SerializedName("double_damage_from") val doubleDamageFrom: List<NameAndUrlResponse>,
    @SerializedName("double_damage_to") val doubleDamageTo: List<NameAndUrlResponse>,
    @SerializedName("half_damage_from") val halfDamageFrom: List<NameAndUrlResponse>,
    @SerializedName("half_damage_to") val halfDamageTo: List<NameAndUrlResponse>,
    @SerializedName("no_damage_from") val noDamageFrom: List<NameAndUrlResponse>,
    @SerializedName("no_damage_to") val noDamageTo: List<NameAndUrlResponse>,
)