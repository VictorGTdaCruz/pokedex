package com.victor.data.mapper

import com.victor.model.Specie
import com.victor.network.response.SpecieResponse

internal fun SpecieResponse.toDomain(): Specie {
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