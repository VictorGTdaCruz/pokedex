package com.victor.pokedex.domain.model

data class TypeDetails(
    val id: Long,
    val name: String,
    val pokemons: List<Pokemon>,
)

data class Pokemon(
    val id: Long,
    val name: String,
    val slot: Int
)