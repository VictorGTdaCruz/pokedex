package com.victor.feature_pokedex.data

import com.victor.feature_pokedex.data.model.EvolutionResponse
import com.victor.feature_pokedex.data.model.GenerationResponse
import com.victor.feature_pokedex.data.model.NameAndUrlResponse
import com.victor.feature_pokedex.data.model.PagedResponse
import com.victor.feature_pokedex.data.model.PokemonResponse
import com.victor.feature_pokedex.data.model.SpecieResponse
import com.victor.feature_pokedex.data.model.TypeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokedexDataSource {

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