package com.victor.feature_pokedex.data

import com.victor.feature_pokedex.data.model.EvolutionsResponse
import com.victor.feature_pokedex.data.model.GenerationResponse
import com.victor.feature_pokedex.data.model.NameAndUrlResponse
import com.victor.feature_pokedex.data.model.PagedResponse
import com.victor.feature_pokedex.data.model.PokemonResponse
import com.victor.feature_pokedex.data.model.SpeciesResponse
import com.victor.feature_pokedex.data.model.TypeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokedexGateway {

    @GET("type")
    suspend fun getPokemonTypes(): PagedResponse<NameAndUrlResponse>

    @GET("type/{id}")
    suspend fun getTypeDetails(@Path("id") typeId: Long): TypeDetailsResponse

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PagedResponse<NameAndUrlResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") pokemonId: Long): PokemonResponse

    @GET("generation/{generation}")
    suspend fun getPokemonListByGeneration(@Path("generation") generation: Int): GenerationResponse

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") pokemonId: Long): SpeciesResponse

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutions(@Path("id") chainId: Long): EvolutionsResponse
}