package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PokemonResponse
import com.victor.pokedex.data.model.TypeDetailsResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class TypeDetailsMapperTest {

    @Test
    fun shouldMapResponseToTypeDetailsDomain() {
        val response = getMockedTypeDetails()
        val domain = response.toDomain()

        assertEquals(0L, domain.id)
        assertEquals("name", domain.name)
        assertEquals(1, domain.pokemons.size)
        assertEquals(3L, domain.pokemons.first().id)
        assertEquals("test", domain.pokemons.first().name)
        assertEquals(1, domain.pokemons.first().slot)
    }

    private fun getMockedTypeDetails() =
        TypeDetailsResponse(
            id = 0L,
            name = "name",
            pokemon = listOf(
                PokemonResponse(
                    slot = 1,
                    pokemon = NameAndUrlResponse(
                        name = "test",
                        url = "pokemon/3"
                    )
                )
            ),
        )
}