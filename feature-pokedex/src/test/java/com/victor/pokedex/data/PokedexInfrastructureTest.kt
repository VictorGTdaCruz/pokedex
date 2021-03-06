package com.victor.pokedex.data

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokedexInfrastructureTest {

    private lateinit var infrastructure: PokedexInfrastructure
    private lateinit var api: PokedexGateway

    @Before
    fun before() {
        api = mockk(relaxed = true)
        infrastructure = PokedexInfrastructure(api)
    }

    @Test
    fun whenGetPokemonTypes_callsApiGetPokemonTypes() {
        runBlocking { infrastructure.getPokemonTypes() }
        coVerify { api.getPokemonTypes() }
    }

    @Test
    fun whenGetTypeDetails_callsApiGetTypeDetails() {
        val type = 0L
        runBlocking { infrastructure.getTypeDetails(type) }
        coVerify { api.getTypeDetails(type) }
    }

    @Test
    fun whenGetPokemonDetails_callsApiGetPokemonDetails() {
        val id = 0L
        runBlocking { infrastructure.getPokemonDetails(id) }
        coVerify { api.getPokemonDetails(id) }
    }
}