package com.victor.feature_pokedex.domain

import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.service.PokedexService

internal class PokedexUseCase(
    private val infrastructure: PokedexService
) {

    companion object {
        private val VALID_TYPE_ID_RANGE = 1 until 9999
        private const val POKEMON_LIST_OFFSET = 0
        private const val POKEMON_LIST_LIMIT = 99999
    }

    suspend fun getPokemonList(
        typeList: List<PokemonType>?,
        indexRange: ClosedFloatingPointRange<Float>?
    ): List<Pokemon> {
        val pokemonList = if (typeList.isNullOrEmpty())
            infrastructure.getPokemonList(offset = POKEMON_LIST_OFFSET, limit = POKEMON_LIST_LIMIT)
        else
            getPokemonFromTypeFilter(typeList)
        return if (indexRange != null)
            pokemonList.applyIndexRangeFilter(indexRange)
        else
            pokemonList
    }

    suspend fun getPokemonDetails(pokemonId: Long) = infrastructure.getPokemonDetails(pokemonId)

    suspend fun getPokemonTypes() =
        infrastructure.getPokemonTypes()
            .filter { it.id in VALID_TYPE_ID_RANGE }
            .sortedBy { it.name }

    private suspend fun getPokemonFromTypeFilter(typeList: List<PokemonType>) =
        mutableListOf<Pokemon>().apply {
            typeList.forEach {
                val type = infrastructure.getTypeDetails(it.id)
                if (isEmpty()) {
                    addAll(type.pokemons)
                } else {
                    val intersectResult = intersect(type.pokemons.toSet())
                    clear()
                    addAll(intersectResult)
                }
            }
        }

    private fun List<Pokemon>.applyIndexRangeFilter(indexRange: ClosedFloatingPointRange<Float>) =
        filter { indexRange.contains(it.id.toFloat()) }
}