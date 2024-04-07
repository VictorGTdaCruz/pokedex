package com.victor.network

import com.victor.network.response.EvolutionResponse
import com.victor.network.response.GenerationResponse
import com.victor.network.response.NameAndUrlResponse
import com.victor.network.response.PagedResponse
import com.victor.network.response.PokemonResponse
import com.victor.network.response.SpecieResponse
import com.victor.network.response.TypeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokedexApi {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 9999,
    ): PagedResponse<NameAndUrlResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") pokemonId: Int): PokemonResponse

    @GET("type")
    suspend fun getTypeList(): PagedResponse<NameAndUrlResponse>

    @GET("type/{id}")
    suspend fun getType(@Path("id") typeId: Int): TypeResponse

    @GET("generation/{generation}")
    suspend fun getGeneration(@Path("generation") generationId: Int): GenerationResponse

    @GET("pokemon-species/{id}")
    suspend fun getSpecie(@Path("id") pokemonId: Int): SpecieResponse

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(@Path("id") chainId: Int): EvolutionResponse
}