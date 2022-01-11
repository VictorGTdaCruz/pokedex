package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PagedResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class TypesMapperTest {

    @Test
    fun shouldMapResponseToPokemonTypesDomain() {
        val response = getMockedTypes()
        val domain = response.toPokemonTypesDomain()

        assertEquals(3, domain.size)
        assertEquals("a", domain.first().name)
        assertEquals(1L, domain.first().id)
        assertEquals("b", domain[1].name)
        assertEquals(2L, domain[1].id)
        assertEquals("c", domain[2].name)
        assertEquals(3L, domain[2].id)
    }

    private fun getMockedTypes() =
        PagedResponse(
            results = listOf(
                NameAndUrlResponse(
                    name = "a",
                    url = "1"
                ),
                NameAndUrlResponse(
                    name = "b",
                    url = "2"
                ),
                NameAndUrlResponse(
                    name = "c",
                    url = "3"
                ),
            )
        )
}