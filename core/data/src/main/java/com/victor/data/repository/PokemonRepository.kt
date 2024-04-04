package com.victor.data.repository

import com.victor.model.Evolution
import com.victor.model.Generation
import com.victor.model.Pokemon
import com.victor.model.PokemonSimple
import com.victor.model.Specie

internal interface PokemonRepository {

    companion object {
        internal val VALID_POKEMON_ID_RANGE = 1 until 9999
    }

    suspend fun getPokemonList(): List<PokemonSimple>

    suspend fun getPokemon(pokemonId: Int): Pokemon

    suspend fun getGeneration(generationId: Int): Generation

    suspend fun getSpecie(pokemonId: Int): Specie

    suspend fun getEvolution(evolutionChainId: Int): Evolution
}