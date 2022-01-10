package com.victor.pokedex.data

import com.victor.networking.request
import com.victor.pokedex.data.mapper.toDomain
import com.victor.pokedex.data.mapper.toPokemonTypesDomain
import com.victor.pokedex.domain.service.PokedexService

internal class PokedexInfrastructure(private val api: PokedexGateway) : PokedexService {

    override suspend fun getPokemonTypes() = request {
        api.getPokemonTypes().toPokemonTypesDomain()
    }

    override suspend fun getTypeDetails(typeId: Long) = request {
        api.getTypeDetails(typeId).toDomain()
    }

    override suspend fun getPokemonDetails(pokemonId: Long) = request {
        api.getPokemonDetails(pokemonId).toDomain()
    }
}