package com.victor.feature_pokedex.domain.service

import androidx.paging.PagingData
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.TypeDetails
import kotlinx.coroutines.flow.Flow

internal interface PokedexService {

    suspend fun getPokemonList(): Flow<PagingData<Pokemon>>

    suspend fun getPokemonTypes(): List<PokemonType>

    suspend fun getTypeDetails(typeId: Long): TypeDetails

    suspend fun getPokemonDetails(pokemonId: Long): PokemonDetails
}