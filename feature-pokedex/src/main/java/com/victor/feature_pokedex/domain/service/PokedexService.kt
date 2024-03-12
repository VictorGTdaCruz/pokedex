package com.victor.feature_pokedex.domain.service

import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonGeneration
import com.victor.feature_pokedex.domain.model.PokemonSpecies
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.TypeDetails

internal interface PokedexService {

    suspend fun getPokemonList(offset: Int, limit: Int): List<Pokemon>

    suspend fun getPokemonTypes(): List<PokemonType>

    suspend fun getTypeDetails(typeId: Long): TypeDetails

    suspend fun getPokemonDetails(pokemonId: Long): PokemonDetails

    suspend fun getPokemonListByGeneration(generation: Int): PokemonGeneration

    suspend fun getPokemonSpecies(pokemonId: Long): PokemonSpecies
}