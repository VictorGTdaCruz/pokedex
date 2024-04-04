package com.victor.feature_pokedex.domain

import com.victor.data.mapper.PokemonInformationMapper
import com.example.model.EvolutionChain
import com.example.model.Pokemon
import com.example.model.PokemonInformation
import com.victor.data.repository.PokemonRepository
import com.victor.data.repository.TypeRepository

internal class PokemonInformationUseCase(
    private val pokemonRepository: com.victor.data.repository.PokemonRepository,
    private val typeRepository: com.victor.data.repository.TypeRepository
) {
    suspend fun getPokemonInformation(pokemonId: Int): com.example.model.PokemonInformation {
        val pokemonSpecies = pokemonRepository.getSpecie(pokemonId)
        val pokemon = pokemonRepository.getPokemon(pokemonId)
        val typeList = typeRepository.getTypeList()
        val typeListOfCurrentPokemon = pokemon.typeList.map { typeRepository.getType(it.id) }
        val evolutionChain = pokemonRepository.getEvolution(pokemonSpecies.evolutionChainId)
        val pokemonListFromEvolutionChain = getPokemonFromEveryPokemonInEvolutionChain(evolutionChain.chain)

        return com.victor.data.mapper.PokemonInformationMapper.map(
            pokemon,
            pokemonSpecies,
            typeList,
            typeListOfCurrentPokemon,
            evolutionChain,
            pokemonListFromEvolutionChain
        )
    }

    private suspend fun getPokemonFromEveryPokemonInEvolutionChain(
        response: com.example.model.EvolutionChain?
    ): MutableList<com.example.model.Pokemon> =
        mutableListOf<com.example.model.Pokemon>().apply {
            val currentId = response?.specieId ?: 0
            add(pokemonRepository.getPokemon(currentId))
            response?.evolvesTo?.forEach {
                addAll(getPokemonFromEveryPokemonInEvolutionChain(it))
            }
        }
}