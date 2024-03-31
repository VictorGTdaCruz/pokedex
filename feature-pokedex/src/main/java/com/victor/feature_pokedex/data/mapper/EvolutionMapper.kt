package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.data.model.EvolutionChainResponse
import com.victor.feature_pokedex.data.model.EvolutionDetailsResponse
import com.victor.feature_pokedex.data.model.EvolutionResponse
import com.victor.feature_pokedex.domain.model.Evolution
import com.victor.feature_pokedex.domain.model.EvolutionChain
import com.victor.feature_pokedex.domain.model.EvolutionDetails

internal fun EvolutionResponse.toDomain() =
    Evolution(
        id = id ?: 0,
        chain = chain?.toDomain(),
    )

private fun EvolutionChainResponse.toDomain() : EvolutionChain =
    EvolutionChain(
        specieId = IdMapper.mapIdFromUrl(species?.url),
        evolvesTo = evolvesTo?.map { it.toDomain() },
        evolutionDetails = evolutionDetails?.map { it.toDomain() },
        isBaby = isBaby,
    )

private fun EvolutionDetailsResponse.toDomain() =
    EvolutionDetails(
        trigger = trigger?.name,
        minLevel = minLevel
    )