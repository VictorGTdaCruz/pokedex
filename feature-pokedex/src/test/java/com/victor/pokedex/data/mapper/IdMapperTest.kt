package com.victor.pokedex.data.mapper

import junit.framework.Assert.assertEquals
import org.junit.Test

class IdMapperTest {

    @Test
    fun shouldMapIdFromTypeUrl() {
        val url = "test/type/3/"
        assertEquals(3, com.victor.feature_pokedex.data.mapper.IdMapper.fromTypeUrl(url))
    }

    @Test
    fun shouldMapIdFromTypeUrl_noSlashAfter() {
        val url = "test/type/3"
        assertEquals(3, com.victor.feature_pokedex.data.mapper.IdMapper.fromTypeUrl(url))
    }

    @Test
    fun shouldMapIdFromTypeUrl_randomAfter() {
        val url = "test/type/3/pokemon"
        assertEquals(3, com.victor.feature_pokedex.data.mapper.IdMapper.fromTypeUrl(url))
    }

    @Test
    fun shouldMapIdFromTypeUrl_returns0IfNotCorrectUrl() {
        val url = "test/3"
        assertEquals(0, com.victor.feature_pokedex.data.mapper.IdMapper.fromTypeUrl(url))
    }

    @Test
    fun shouldMapIdFromPokemonUrl() {
        val url = "test/pokemon/2/"
        assertEquals(2, com.victor.feature_pokedex.data.mapper.IdMapper.fromPokemonUrl(url))
    }

    @Test
    fun shouldMapIdFromPokemonUrl_noSlashAfter() {
        val url = "test/pokemon/2"
        assertEquals(2, com.victor.feature_pokedex.data.mapper.IdMapper.fromPokemonUrl(url))
    }

    @Test
    fun shouldMapIdFromPokemonUrl_randomAfter() {
        val url = "test/pokemon/2/type"
        assertEquals(2, com.victor.feature_pokedex.data.mapper.IdMapper.fromPokemonUrl(url))
    }

    @Test
    fun shouldMapIdFromPokemonUrl_returns0IfNotCorrectUrl() {
        val url = "test/2"
        assertEquals(0, com.victor.feature_pokedex.data.mapper.IdMapper.fromPokemonUrl(url))
    }
}