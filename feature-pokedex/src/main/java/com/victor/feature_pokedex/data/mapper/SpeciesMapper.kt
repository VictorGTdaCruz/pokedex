package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.SpeciesResponse
import com.victor.feature_pokedex.domain.model.PokemonSpecies
import java.util.Locale

internal fun SpeciesResponse.toDomain() =
    PokemonSpecies(
        id = id ?: 0,
        captureRate = captureRate ?: 0,
        captureProbability = 0f,
        growthRate = growthRate?.name ?: "",
        flavorText = flavorTextList?.find { it.version?.name == "ruby" }?.flavorText ?: "",
        genera = genera?.find { it.language?.name == "en" }?.genus ?: "",
        genderRate = genderRate ?: 0,
        eggGroups = eggGroups?.map { it.name ?: "" } ?: emptyList(),
        hatchCounter = hatchCounter ?: 0,
    )