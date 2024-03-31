package com.victor.feature_pokedex.domain

import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.TypeSimple
import com.victor.feature_pokedex.domain.service.PokemonRepository
import com.victor.feature_pokedex.domain.service.TypeRepository
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.Sort

internal class PokemonListUseCase(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository
) {

    companion object {
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
                add(pokemonRepository.getGeneration(selectedGeneration).pokemonList)
            typeList.forEach {
                add(typeRepository.getType(it.id).pokemonList)
            }
            if (isEmpty())
                add(pokemonRepository.getPokemonList(offset = POKEMON_LIST_OFFSET, limit = POKEMON_LIST_LIMIT))
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

        return sort(result.applyIndexRangeFilter(indexRange), sort)
    }

    private fun List<PokemonSimple>.applyIndexRangeFilter(indexRange: ClosedFloatingPointRange<Float>?): List<PokemonSimple> {
        indexRange?.let {
            val range = it.start.toInt()..it.endInclusive.toInt()
            return filter { pokemon -> range.contains(pokemon.id) }
        }
        return this
    }

    fun sort(pokemonList: List<PokemonSimple>, sort: Sort) = pokemonList.run {
        when (sort) {
            Sort.SmallestNumberFirst -> sortedBy { it.id }
            Sort.HighestNumberFirst -> sortedByDescending { it.id }
            Sort.AtoZ -> sortedBy { it.name }
            Sort.ZtoA -> sortedByDescending { it.name }
        }
    }
}