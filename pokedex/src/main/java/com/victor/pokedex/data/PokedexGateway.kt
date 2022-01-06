package com.victor.pokedex.data

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PagedResponse
import com.victor.pokedex.data.model.PokemonTypeResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PokedexGateway {

    @GET("type")
    suspend fun getPokemonTypes() : PagedResponse<NameAndUrlResponse>

    @GET("type/{id}")
    suspend fun getPokemonType(@Path("id") typeId: String) : PokemonTypeResponse
}