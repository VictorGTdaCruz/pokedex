package com.victor.model

data class Type(
    val id: Int,
    val name: String,
    val damageRelations: DamageRelations,
    val pokemonList: List<PokemonSimple>,
)

data class DamageRelations(
    val doubleDamageFrom: List<TypeSimple>,
    val doubleDamageTo: List<TypeSimple>,
    val halfDamageFrom: List<TypeSimple>,
    val halfDamageTo: List<TypeSimple>,
    val noDamageFrom: List<TypeSimple>,
    val noDamageTo: List<TypeSimple>,
)

data class TypeSimple(
    val id: Int,
    val name: String
)