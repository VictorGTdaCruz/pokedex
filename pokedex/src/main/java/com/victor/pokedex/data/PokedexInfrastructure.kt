package com.victor.pokedex.data

import com.victor.pokedex.data.mapper.toDomain
import com.victor.pokedex.data.mapper.toPokemonTypesDomain
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.coroutineScope

internal class PokedexInfrastructure(private val api: PokedexGateway): PokedexService {

    override suspend fun getPokemonTypes() = coroutineScope {
        api.getPokemonTypes().toPokemonTypesDomain()
    }

    override suspend fun getPokemonType(id: Long) = coroutineScope {
        api.getPokemonType(id).toDomain()
    }
}