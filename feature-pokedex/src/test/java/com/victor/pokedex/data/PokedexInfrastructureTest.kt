package com.victor.pokedex.data

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokedexInfrastructureTest {

    private lateinit var infrastructure: com.victor.feature_pokedex.data.PokedexInfrastructure
    private lateinit var api: com.victor.feature_pokedex.data.PokedexGateway

    @Before
    fun before() {
        api = mockk(relaxed = true)
        infrastructure = com.victor.feature_pokedex.data.PokedexInfrastructure(api)
    }

    @Test
    fun whenGetPokemonTypes_callsApiGetPokemonTypes() {
        runBlocking { infrastructure.getTypeList() }
        coVerify { api.getTypeList() }
    }

    @Test
    fun whenGetTypeDetails_callsApiGetTypeDetails() {
        val type = 0L
        runBlocking { infrastructure.getType(type) }
        coVerify { api.getType(type) }
    }

    @Test
    fun whenGetPokemon_callsApiGetPokemon() {
        val id = 0L
        runBlocking { infrastructure.getPokemon(id) }
        coVerify { api.getPokemon(id) }
    }
}