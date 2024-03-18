package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.SpeciesResponse
import com.victor.feature_pokedex.domain.model.Specie

internal fun SpeciesResponse.toDomain(): Specie {
    val flavorText = flavorTextList?.find { it.version?.name == "ruby" }?.flavorText ?: ""
    val genera = genera?.find { it.language?.name == "en" }?.genus ?: ""
    val eggGroups = eggGroups?.map { it.name ?: "" }

    return Specie(
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