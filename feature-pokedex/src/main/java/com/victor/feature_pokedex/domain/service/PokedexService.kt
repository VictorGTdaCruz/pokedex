package com.victor.feature_pokedex.domain.service

import com.victor.feature_pokedex.domain.model.Generation
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.Specie
import com.victor.feature_pokedex.domain.model.Type
import com.victor.feature_pokedex.domain.model.TypeSimple

internal interface PokedexService {

    suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonSimple>

    suspend fun getPokemon(pokemonId: Long): Pokemon

    suspend fun getTypeList(): List<TypeSimple>

    suspend fun getType(typeId: Long): Type

    suspend fun getGeneration(generationId: Int): Generation

    suspend fun getSpecie(pokemonId: Long): Specie

    suspend fun getPokemonInformation(pokemonId: Long): PokemonInformation
}