package com.victor.feature_pokedex.data

import com.victor.feature_pokedex.data.mapper.PokemonInformationMapper
import com.victor.feature_pokedex.data.mapper.toDomain
import com.victor.feature_pokedex.data.mapper.toPokemonListDomain
import com.victor.feature_pokedex.data.mapper.toPokemonTypesDomain
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.networking.request

internal class PokedexInfrastructure(private val api: PokedexGateway) : PokedexService {

    override suspend fun getPokemonList(offset: Int, limit: Int) = request {
        api.getPokemonList(offset = offset, limit = limit).toPokemonListDomain()
    }

    //TODO bring all rules from usecase to here
    override suspend fun getPokemonTypes() = request {
        api.getPokemonTypes()
            .toPokemonTypesDomain()
            .filter { it.id in 1 until 9999 }
            .sortedBy { it.id }
    }

    override suspend fun getTypeDetails(typeId: Long) = request {
        api.getTypeDetails(typeId).toDomain()
    }

    override suspend fun getPokemon(pokemonId: Long) = request {
        api.getPokemon(pokemonId).toDomain()
    }

    override suspend fun getPokemonListByGeneration(generation: Int) = request {
        api.getPokemonListByGeneration(generation).toDomain()
    }

    override suspend fun getPokemonSpecies(pokemonId: Long) = request {
        api.getPokemonSpecies(pokemonId).toDomain()
    }

    override suspend fun getPokemonInformation(pokemonId: Long): PokemonInformation {
        val pokemonSpecies = getPokemonSpecies(pokemonId)
        val pokemon = getPokemon(pokemonId)
        val typeList = getPokemonTypes()
        val pokemonTypeList = pokemon.types.map { getTypeDetails(it.type.id) }

        return PokemonInformationMapper.map(pokemon, pokemonSpecies, typeList, pokemonTypeList)
    }
}