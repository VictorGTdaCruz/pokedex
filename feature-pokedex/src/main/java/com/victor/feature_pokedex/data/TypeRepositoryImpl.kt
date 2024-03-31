package com.victor.feature_pokedex.data

import com.victor.feature_pokedex.data.mapper.toDomain
import com.victor.feature_pokedex.data.mapper.toPokemonTypesDomain
import com.victor.feature_pokedex.domain.service.PokemonRepository
import com.victor.feature_pokedex.domain.service.TypeRepository
import com.victor.feature_pokedex.domain.service.TypeRepository.Companion.VALID_TYPE_ID_RANGE
import com.victor.networking.request

internal class TypeRepositoryImpl(private val api: PokedexDataSource) : TypeRepository {

    override suspend fun getTypeList() = request {
        api.getTypeList()
            .toPokemonTypesDomain()
            .filter { it.id in VALID_TYPE_ID_RANGE }
            .sortedBy { it.id }
    }

    override suspend fun getType(typeId: Int) = request {
        api.getType(typeId).toDomain(PokemonRepository.VALID_POKEMON_ID_RANGE)
    }
}