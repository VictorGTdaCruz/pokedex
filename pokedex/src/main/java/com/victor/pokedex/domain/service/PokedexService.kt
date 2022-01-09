package com.victor.pokedex.domain.service

import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.model.PokemonTypeSimplified

internal interface PokedexService {

    suspend fun getPokemonTypes(): List<PokemonTypeSimplified>

    suspend fun getPokemonType(typeId: Long): PokemonType

    suspend fun getPokemonDetails(pokemonId: Long): PokemonDetails
}