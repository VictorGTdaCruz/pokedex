package com.victor.data.mapper

import com.victor.model.Evolution
import com.victor.model.EvolutionChain
import com.victor.model.EvolutionDetails
import com.victor.network.response.EvolutionChainResponse
import com.victor.network.response.EvolutionDetailsResponse
import com.victor.network.response.EvolutionResponse

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