package com.victor.pokedex.data

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PagedResponse
import com.victor.pokedex.data.model.PokemonDetailsResponse
import com.victor.pokedex.data.model.TypeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PokedexGateway {

    @GET("type")
    suspend fun getPokemonTypes(): PagedResponse<NameAndUrlResponse>

    @GET("type/{id}")
    suspend fun getTypeDetails(@Path("id") typeId: Long): TypeDetailsResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokemonId: Long): PokemonDetailsResponse
}