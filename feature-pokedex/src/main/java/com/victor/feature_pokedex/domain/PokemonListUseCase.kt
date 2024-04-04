package com.victor.feature_pokedex.domain

import com.example.model.PokemonSimple
import com.example.model.TypeSimple
import com.victor.data.repository.PokemonRepository
import com.victor.data.repository.TypeRepository
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.Sort

internal class PokemonListUseCase(
    private val pokemonRepository: com.victor.data.repository.PokemonRepository,
    private val typeRepository: com.victor.data.repository.TypeRepository
) {

    // TODO remove this key from here?
    companion object {
        internal val SELECTABLE_POKEMON_GENERATION_RANGE = 1..8
    }

    suspend fun getPokemonList(
        typeList: List<com.example.model.TypeSimple>,
        selectedGeneration: Int?,
        indexRange: ClosedFloatingPointRange<Float>?,
        sort: Sort,
    ): List<com.example.model.PokemonSimple> {
        val pokemonLists = mutableListOf<List<com.example.model.PokemonSimple>>().apply {
            if (selectedGeneration != null)
                add(pokemonRepository.getGeneration(selectedGeneration).pokemonList)
            typeList.forEach {
                add(typeRepository.getType(it.id).pokemonList)
            }
            if (isEmpty())
                add(pokemonRepository.getPokemonList())
        }

        val result = mutableListOf<com.example.model.PokemonSimple>().apply {
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

    private fun List<com.example.model.PokemonSimple>.applyIndexRangeFilter(indexRange: ClosedFloatingPointRange<Float>?): List<com.example.model.PokemonSimple> {
        indexRange?.let {
            val range = it.start.toInt()..it.endInclusive.toInt()
            return filter { pokemon -> range.contains(pokemon.id) }
        }
        return this
    }

    fun sort(pokemonList: List<com.example.model.PokemonSimple>, sort: Sort) = pokemonList.run {
        when (sort) {
            Sort.SmallestNumberFirst -> sortedBy { it.id }
            Sort.HighestNumberFirst -> sortedByDescending { it.id }
            Sort.AtoZ -> sortedBy { it.name }
            Sort.ZtoA -> sortedByDescending { it.name }
        }
    }
}