package com.victor.data.repository

import com.victor.data.mapper.toDomain
import com.victor.data.mapper.toPokemonListDomain
import com.victor.data.repository.PokemonRepository.Companion.VALID_POKEMON_ID_RANGE
import com.victor.network.PokedexDataSource

internal class RemotePokemonRepository(private val dataSource: PokedexDataSource): PokemonRepository {

    override suspend fun getPokemonList() =
        dataSource.getPokemonList()
            .toPokemonListDomain()
            .filter { it.id in VALID_POKEMON_ID_RANGE }

    override suspend fun getPokemon(pokemonId: Int) =
        dataSource.getPokemon(pokemonId).toDomain()

    override suspend fun getGeneration(generationId: Int) =
        dataSource.getGeneration(generationId).toDomain()

    override suspend fun getSpecie(pokemonId: Int) =
        dataSource.getSpecie(pokemonId).toDomain()

    override suspend fun getEvolution(evolutionChainId: Int) =
        dataSource.getEvolution(evolutionChainId).toDomain()
}