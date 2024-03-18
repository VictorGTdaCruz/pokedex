package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PokemonOfficialArtworkSpriteResponse
import com.victor.pokedex.data.model.PokemonOtherSpritesResponse
import com.victor.pokedex.data.model.PokemonResponse
import com.victor.pokedex.data.model.PokemonSpriteResponse
import com.victor.pokedex.data.model.PokemonStatsResponse
import com.victor.pokedex.data.model.PokemonTypeWithSlotResponse
import junit.framework.Assert.assertEquals
import org.junit.Test

class PokemonMapperTest {

    @Test
    fun shouldMapResponseToPokemonDomain() {
        val response = getMockedPokemonResponse()
        val domain = response.toDomain()
        assertEquals(0L, domain.id)
        assertEquals("pokemon_name", domain.name)
        assertEquals(1, domain.height)
        assertEquals(2, domain.weight)
        assertEquals(1, domain.types.size)
        assertEquals(0, domain.types.first().slot)
        assertEquals(4L, domain.types.first().type.id)
        assertEquals("name", domain.types.first().type.name)
        assertEquals("front_default", domain.sprites.frontDefault)
        assertEquals("official", domain.sprites.otherFrontDefault)
    }

    private fun getMockedPokemonResponse() =
        PokemonResponse(
            id = 0L,
            name = "pokemon_name",
            height = 1,
            weight = 2,
            types = listOf(
                PokemonTypeWithSlotResponse(
                    slot = 0,
                    type = NameAndUrlResponse(
                        name = "name",
                        url = "type/4"
                    )
                )
            ),
            stats = listOf(
                PokemonStatsResponse(
                    base_stat = 1,
                    effort = 0,
                    stat = NameAndUrlResponse(
                        name = "stat",
                        url = "url"
                    )
                )
            ),
            sprites = PokemonSpriteResponse(
                frontDefault = "front_default",
                other = PokemonOtherSpritesResponse(
                    officialArtwork = PokemonOfficialArtworkSpriteResponse(
                        frontDefault = "official"
                    )
                )
            ),
        )
}