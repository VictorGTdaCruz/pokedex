package com.victor.data.repository

import com.victor.data.mapper.toDomain
import com.victor.data.mapper.toPokemonTypesDomain
import com.victor.data.repository.TypeRepository.Companion.VALID_TYPE_ID_RANGE
import com.victor.network.PokedexDataSource

class RemoteTypeRepository(private val dataSource: PokedexDataSource): TypeRepository {

    override suspend fun getTypeList() =
        dataSource.getTypeList()
            .toPokemonTypesDomain()
            .filter { it.id in VALID_TYPE_ID_RANGE }
            .sortedBy { it.id }

    override suspend fun getType(typeId: Int) =
        dataSource.getType(typeId).toDomain(PokemonRepository.VALID_POKEMON_ID_RANGE)
}