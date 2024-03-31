package com.victor.feature_pokedex.domain.service

import com.victor.feature_pokedex.domain.model.Evolution
import com.victor.feature_pokedex.domain.model.Generation
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.Specie

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