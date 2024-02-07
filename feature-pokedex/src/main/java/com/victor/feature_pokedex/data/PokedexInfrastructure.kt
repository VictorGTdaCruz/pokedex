package com.victor.feature_pokedex.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.victor.feature_pokedex.data.mapper.toDomain
import com.victor.feature_pokedex.data.mapper.toPokemonTypesDomain
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.networking.request

internal class PokedexInfrastructure(private val api: PokedexGateway) : PokedexService {

    override suspend fun getPokemonList() = Pager(PagingConfig(pageSize = 20)) {
        PokemonPagingDataSource(api)
    }.flow

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