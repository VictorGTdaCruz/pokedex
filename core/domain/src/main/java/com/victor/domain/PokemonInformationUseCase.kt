package com.victor.domain

import com.victor.data.mapper.PokemonInformationMapper
import com.victor.data.repository.PokemonRepository
import com.victor.data.repository.TypeRepository
import com.victor.model.EvolutionChain
import com.victor.model.Pokemon
import com.victor.model.PokemonInformation

class PokemonInformationUseCase(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository
) {
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