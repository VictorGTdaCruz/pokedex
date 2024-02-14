package com.victor.feature_pokedex.domain

import com.victor.feature_pokedex.domain.service.PokedexService

internal class PokedexUseCase(
    private val infrastructure: PokedexService
) {

    companion object {
        private val VALID_TYPE_ID_RANGE = 1 until 9999
        private const val POKEMON_LIST_OFFSET = 0
        private const val POKEMON_LIST_LIMIT = 9999
    }

    suspend fun getPokemonList() =
        infrastructure.getPokemonList(offset = POKEMON_LIST_OFFSET, limit = POKEMON_LIST_LIMIT)

    suspend fun getPokemonDetails(pokemonId: Long) = infrastructure.getPokemonDetails(pokemonId)

    suspend fun getPokemonTypes() =
        infrastructure.getPokemonTypes()
            .filter { it.id in VALID_TYPE_ID_RANGE }
            .sortedBy { it.name }

    suspend fun getTypeDetails(typeId: Long) = infrastructure.getTypeDetails(typeId)
}