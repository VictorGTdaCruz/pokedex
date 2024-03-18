package com.victor.feature_pokedex.domain.model

data class Type(
    val id: Long,
    val name: String,
    val damageRelations: DamageRelations,
    val pokemonList: List<PokemonSimple>,
)

data class PokemonSimple(
    val id: Long,
    val name: String,
)

data class DamageRelations(
    val doubleDamageFrom: List<TypeSimple>,
    val doubleDamageTo: List<TypeSimple>,
    val halfDamageFrom: List<TypeSimple>,
    val halfDamageTo: List<TypeSimple>,
    val noDamageFrom: List<TypeSimple>,
    val noDamageTo: List<TypeSimple>,
)