package com.victor.pokedex.data

import com.victor.pokedex.data.mapper.toDomain
import com.victor.pokedex.data.mapper.toPokemonTypesDomain
import com.victor.pokedex.domain.service.PokedexService

internal class PokedexInfrastructure(private val api: PokedexGateway): PokedexService {

    override suspend fun getPokemonTypes() = api.getPokemonTypes().toPokemonTypesDomain()

    override suspend fun getPokemonType(id: Long) = api.getPokemonType(id).toDomain()
}