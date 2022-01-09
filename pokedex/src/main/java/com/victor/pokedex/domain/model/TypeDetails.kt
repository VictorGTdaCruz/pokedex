package com.victor.pokedex.domain.model

data class TypeDetails(
    val id: Long,
    val name: String,
    val damageRelations: DamageRelations,
    val pastDamageRelations: List<PastDamageRelations>,
    val gameIndices: List<GameIndex>,
    val generation: Generation,
    val moveDamageClass: MoveDamageClass,
    val names: List<PokemonName>,
    val pokemons: List<Pokemon>,
    val moves: List<PokemonMove>
)

data class DamageRelations(
    val noDamageTo: List<PokemonType>,
    val halfDamageTo: List<PokemonType>,
    val doubleDamageTo: List<PokemonType>,
    val noDamageFrom: List<PokemonType>,
    val halfDamageFrom: List<PokemonType>,
    val doubleDamageFrom: List<PokemonType>
)

data class PastDamageRelations(
    val damageRelations: DamageRelations,
    val generation: Generation
)

data class GameIndex(
    val index: Int,
    val url: String
)

data class Generation(
    val name: String,
    val url: String
)

data class MoveDamageClass(
    val name: String,
    val url: String
)

data class Pokemon(
    val id: Long,
    val name: String,
    val slot: Int
)

data class PokemonName(
    val name: String,
    val language: Language
)

data class Language(
    val name: String,
    val url: String
)

data class PokemonMove(
    val name: String,
    val url: String
)