package com.victor.feature_pokedex.data

import com.victor.feature_pokedex.data.mapper.toDomain
import com.victor.feature_pokedex.data.mapper.toPokemonListDomain
import com.victor.feature_pokedex.domain.service.PokemonRepository
import com.victor.feature_pokedex.domain.service.PokemonRepository.Companion.VALID_POKEMON_ID_RANGE
import com.victor.networking.request

internal class PokemonRepositoryImpl(private val api: PokedexDataSource) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int) = request {
        api.getPokemonList(offset = offset, limit = limit)
            .toPokemonListDomain()
            .filter { it.id in VALID_POKEMON_ID_RANGE }
    }

    override suspend fun getPokemon(pokemonId: Int) = request {
        api.getPokemon(pokemonId).toDomain()
    }

    override suspend fun getGeneration(generationId: Int) = request {
        api.getGeneration(generationId).toDomain()
    }

    override suspend fun getSpecie(pokemonId: Int) = request {
        api.getSpecie(pokemonId).toDomain()
    }

    override suspend fun getEvolution(evolutionChainId: Int) = request {
        api.getEvolutionChain(evolutionChainId).toDomain()
    }
}