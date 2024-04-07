package com.victor.domain

import com.victor.data.repository.PokemonRepository
import com.victor.data.repository.TypeRepository
import com.victor.model.PokemonSimple
import com.victor.model.TypeSimple

class PokemonListUseCase(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository
) {

    // TODO remove this key from here?
    companion object {
        val SELECTABLE_POKEMON_GENERATION_RANGE = 1..8
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
                add(pokemonRepository.getPokemonList())
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

sealed class Sort {
    object SmallestNumberFirst : Sort()
    object HighestNumberFirst : Sort()
    object AtoZ : Sort()
    object ZtoA : Sort()
}