package com.victor.pokedex.domain.service

import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.model.TypeDetails

internal interface PokedexService {

    suspend fun getPokemonTypes(): List<PokemonType>

    suspend fun getTypeDetails(typeId: Long): TypeDetails

    suspend fun getPokemonDetails(pokemonId: Long): PokemonDetails
}