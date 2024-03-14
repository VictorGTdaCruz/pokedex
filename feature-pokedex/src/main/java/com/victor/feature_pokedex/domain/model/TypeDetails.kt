package com.victor.feature_pokedex.domain.model

data class TypeDetails(
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
    val doubleDamageFrom: List<PokemonType>,
    val doubleDamageTo: List<PokemonType>,
    val halfDamageFrom: List<PokemonType>,
    val halfDamageTo: List<PokemonType>,
    val noDamageFrom: List<PokemonType>,
    val noDamageTo: List<PokemonType>,
)