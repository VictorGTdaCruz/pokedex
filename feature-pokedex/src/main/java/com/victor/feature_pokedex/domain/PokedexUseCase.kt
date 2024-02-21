package com.victor.feature_pokedex.domain

import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.Sort

internal class PokedexUseCase(
    private val infrastructure: PokedexService
) {

    companion object {
        private val VALID_TYPE_ID_RANGE = 1 until 9999
        private val VALID_POKEMON_ID_RANGE = 1 until 9999
        internal val SELECTABLE_POKEMON_GENERATION_RANGE = 1 .. 8
        private const val POKEMON_LIST_OFFSET = 0
        private const val POKEMON_LIST_LIMIT = 9999
    }

    suspend fun getPokemonList(
        typeList: List<PokemonType>,
        selectedGeneration: Int?,
        indexRange: ClosedFloatingPointRange<Float>?,
        sort: Sort,
    ): List<Pokemon> {
        val pokemonLists = mutableListOf<List<Pokemon>>().apply {
            if (selectedGeneration != null)
                add(infrastructure.getPokemonListByGeneration(selectedGeneration).pokemonList)
            typeList.forEach {
                add(infrastructure.getTypeDetails(it.id).pokemons)
            }
            if (isEmpty())
                add(infrastructure.getPokemonList(offset = POKEMON_LIST_OFFSET, limit = POKEMON_LIST_LIMIT))
        }

        val result = mutableListOf<Pokemon>().apply {
            pokemonLists.forEach {
                if (isEmpty()) {
                    addAll(it)
                } else {
                    val intersectResult = intersect(it.toSet())
                    clear()
                    addAll(intersectResult)
                }
            }
        }

        return sort(result.applyValidPokemonFilter().applyIndexRangeFilter(indexRange), sort)
    }

    suspend fun getPokemonDetails(pokemonId: Long) = infrastructure.getPokemonDetails(pokemonId)

    suspend fun getPokemonTypes() =
        infrastructure.getPokemonTypes()
            .filter { it.id in VALID_TYPE_ID_RANGE }
            .sortedBy { it.name }

    private fun List<Pokemon>.applyIndexRangeFilter(indexRange: ClosedFloatingPointRange<Float>?): List<Pokemon> {
        val intRange = if (indexRange == null)
            VALID_POKEMON_ID_RANGE
        else
            indexRange.start.toInt()..indexRange.endInclusive.toInt()
        return filter { intRange.contains(it.id) }
    }

    fun sort(pokemonList: List<Pokemon>, sort: Sort) = pokemonList.run {
        when (sort) {
            Sort.SmallestNumberFirst -> sortedBy { it.id }
            Sort.HighestNumberFirst -> sortedByDescending { it.id }
            Sort.AtoZ -> sortedBy { it.name }
            Sort.ZtoA -> sortedByDescending { it.name }
        }
    }

    private fun List<Pokemon>.applyValidPokemonFilter() =
        filter { VALID_POKEMON_ID_RANGE.contains(it.id) }
}