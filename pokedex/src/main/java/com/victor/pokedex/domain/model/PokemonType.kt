package com.victor.pokedex.domain.model

data class PokemonType(
    val id: Long,
    val name: String,
    val damageRelations: DamageRelations,
    val pastDamageRelations: List<PastDamageRelations>,
    val gameIndices: List<GameIndex>,
    val generation: Generation,
    val moveDamageClass: MoveDamageClass,
    val names: List<PokemonName>,
    val pokemons: List<PokemonSimplified>,
    val moves: List<PokemonMove>
)

data class DamageRelations(
    val noDamageTo: List<PokemonTypeSimplified>,
    val halfDamageTo: List<PokemonTypeSimplified>,
    val doubleDamageTo: List<PokemonTypeSimplified>,
    val noDamageFrom: List<PokemonTypeSimplified>,
    val halfDamageFrom: List<PokemonTypeSimplified>,
    val doubleDamageFrom: List<PokemonTypeSimplified>
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

data class PokemonSimplified(
    val name: String,
    val url: String,
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