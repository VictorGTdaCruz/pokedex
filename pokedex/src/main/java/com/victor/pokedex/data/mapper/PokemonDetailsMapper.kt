package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PokemonDetailsResponse
import com.victor.pokedex.data.model.Tst
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonSprite
import com.victor.pokedex.domain.model.Tste
import com.victor.pokedex.domain.model.Tstet

internal fun PokemonDetailsResponse.toDomain() =
    PokemonDetails(
        id = id ?: 0,
        name = name.orEmpty(),
        types = types?.toTstDomain() ?: emptyList(),
        sprites = PokemonSprite(sprites?.frontDefault.orEmpty())
    )

internal fun List<Tst>.toTstDomain() = map {
    Tste(
        slot = it.slot ?: 0,
        type = it.type?.toTypeDomain() ?: Tstet("", "")
    )
}

internal fun NameAndUrlResponse.toTypeDomain() = Tstet(
        name = name.orEmpty(),
        url = url.orEmpty()
    )