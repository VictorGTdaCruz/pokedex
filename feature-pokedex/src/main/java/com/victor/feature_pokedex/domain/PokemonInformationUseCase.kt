package com.victor.feature_pokedex.domain

import com.victor.feature_pokedex.data.mapper.PokemonInformationMapper
import com.victor.feature_pokedex.domain.model.EvolutionChain
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.service.PokemonRepository
import com.victor.feature_pokedex.domain.service.TypeRepository

internal class PokemonInformationUseCase(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository
) {

    companion object {
        internal val SELECTABLE_POKEMON_GENERATION_RANGE = 1..8
        private const val POKEMON_LIST_OFFSET = 0
        private const val POKEMON_LIST_LIMIT = 9999
    }

    suspend fun getPokemonInformation(pokemonId: Int): PokemonInformation {
        val pokemonSpecies = pokemonRepository.getSpecie(pokemonId)
        val pokemon = pokemonRepository.getPokemon(pokemonId)
        val typeList = typeRepository.getTypeList()
        val typeListOfCurrentPokemon = pokemon.typeList.map { typeRepository.getType(it.id) }
        val evolutionChain = pokemonRepository.getEvolution(pokemonSpecies.evolutionChainId)
        val pokemonListFromEvolutionChain = getPokemonFromEveryPokemonInEvolutionChain(evolutionChain.chain)

        return PokemonInformationMapper.map(
            pokemon,
            pokemonSpecies,
            typeList,
            typeListOfCurrentPokemon,
            evolutionChain,
            pokemonListFromEvolutionChain
        )
    }

    private suspend fun getPokemonFromEveryPokemonInEvolutionChain(
        response: EvolutionChain?
    ): MutableList<Pokemon> =
        mutableListOf<Pokemon>().apply {
            val currentId = response?.specieId ?: 0
            add(pokemonRepository.getPokemon(currentId))
            response?.evolvesTo?.forEach {
                addAll(getPokemonFromEveryPokemonInEvolutionChain(it))
            }
        }
}