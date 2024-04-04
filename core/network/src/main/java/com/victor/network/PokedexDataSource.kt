package com.victor.network

import com.victor.network.response.EvolutionResponse
import com.victor.network.response.GenerationResponse
import com.victor.network.response.NameAndUrlResponse
import com.victor.network.response.PagedResponse
import com.victor.network.response.PokemonResponse
import com.victor.network.response.SpecieResponse
import com.victor.network.response.TypeResponse

interface PokedexDataSource {
    suspend fun getPokemonList(): PagedResponse<NameAndUrlResponse>
    suspend fun getPokemon(pokemonId: Int): PokemonResponse
    suspend fun getGeneration(generationId: Int): GenerationResponse
    suspend fun getSpecie(pokemonId: Int): SpecieResponse
    suspend fun getEvolution(evolutionChainId: Int): EvolutionResponse
    suspend fun getTypeList(): PagedResponse<NameAndUrlResponse>
    suspend fun getType(typeId: Int): TypeResponse
}