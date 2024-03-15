package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.SpeciesResponse
import com.victor.feature_pokedex.domain.model.PokemonSpecies

internal fun SpeciesResponse.toDomain(): PokemonSpecies {
    val flavorText = flavorTextList?.find { it.version?.name == "ruby" }?.flavorText ?: ""
    val genera = genera?.find { it.language?.name == "en" }?.genus ?: ""
    val eggGroups = eggGroups?.map { it.name ?: "" }

    return PokemonSpecies(
        id = id ?: 0,
        captureRate = captureRate ?: 0,
        growthRate = growthRate?.name ?: "",
        flavorText = flavorText,
        genera = genera,
        genderRate = genderRate ?: 0,
        eggGroups = eggGroups ?: emptyList(),
        hatchCounter = hatchCounter ?: 0,
        evolutionChainId = IdMapper.mapIdFromUrl(evolutionChain?.url)
    )
}