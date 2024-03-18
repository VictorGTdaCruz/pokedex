package com.victor.feature_pokedex.domain

import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.TypeSimple
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.Sort

internal class PokedexUseCase(
    private val infrastructure: PokedexService
) {

    companion object {
        private val VALID_TYPE_ID_RANGE = 1 until 9999
        private val VALID_POKEMON_ID_RANGE = 1 until 9999
        internal val SELECTABLE_POKEMON_GENERATION_RANGE = 1..8
        private const val POKEMON_LIST_OFFSET = 0
        private const val POKEMON_LIST_LIMIT = 9999
    }

    suspend fun getPokemonList(
        typeList: List<TypeSimple>,
        selectedGeneration: Int?,
        indexRange: ClosedFloatingPointRange<Float>?,
        sort: Sort,
    ): List<PokemonSimple> {
        val pokemonLists = mutableListOf<List<PokemonSimple>>().apply {
            if (selectedGeneration != null)
                add(infrastructure.getGeneration(selectedGeneration).pokemonList)
            typeList.forEach {
                add(infrastructure.getType(it.id).pokemonList)
            }
            if (isEmpty())
                add(infrastructure.getPokemonList(offset = POKEMON_LIST_OFFSET, limit = POKEMON_LIST_LIMIT))
        }

        val result = mutableListOf<PokemonSimple>().apply {
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

    suspend fun getPokemon(pokemonId: Long) = infrastructure.getPokemon(pokemonId)

    suspend fun getPokemonInformation(pokemonId: Long) = infrastructure.getPokemonInformation(pokemonId)

    suspend fun getTypeList() =
        infrastructure.getTypeList()
            .filter { it.id in VALID_TYPE_ID_RANGE }
            .sortedBy { it.name }

    private fun List<PokemonSimple>.applyIndexRangeFilter(indexRange: ClosedFloatingPointRange<Float>?): List<PokemonSimple> {
        val intRange = if (indexRange == null)
            VALID_POKEMON_ID_RANGE
        else
            indexRange.start.toInt()..indexRange.endInclusive.toInt()
        return filter { intRange.contains(it.id) }
    }

    fun sort(pokemonList: List<PokemonSimple>, sort: Sort) = pokemonList.run {
        when (sort) {
            Sort.SmallestNumberFirst -> sortedBy { it.id }
            Sort.HighestNumberFirst -> sortedByDescending { it.id }
            Sort.AtoZ -> sortedBy { it.name }
            Sort.ZtoA -> sortedByDescending { it.name }
        }
    }

    private fun List<PokemonSimple>.applyValidPokemonFilter() =
        filter { VALID_POKEMON_ID_RANGE.contains(it.id) }
}