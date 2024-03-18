package com.victor.feature_pokedex.domain.service

import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonGeneration
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.PokemonSpecies
import com.victor.feature_pokedex.domain.model.TypeSimple
import com.victor.feature_pokedex.domain.model.TypeDetails

internal interface PokedexService {

    suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonSimple>

    suspend fun getTypeList(): List<TypeSimple>

    suspend fun getTypeDetails(typeId: Long): TypeDetails

    suspend fun getPokemon(pokemonId: Long): Pokemon

    suspend fun getPokemonListByGeneration(generation: Int): PokemonGeneration

    suspend fun getPokemonSpecies(pokemonId: Long): PokemonSpecies

    suspend fun getPokemonInformation(pokemonId: Long): PokemonInformation
}