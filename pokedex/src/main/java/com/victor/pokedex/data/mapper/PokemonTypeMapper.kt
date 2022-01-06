package com.victor.pokedex.data.mapper

import com.victor.pokedex.data.model.DamageRelationsResponse
import com.victor.pokedex.data.model.GameIndexResponse
import com.victor.pokedex.data.model.NameAndUrlResponse
import com.victor.pokedex.data.model.PastDamageRelationsResponse
import com.victor.pokedex.data.model.PokemonNameResponse
import com.victor.pokedex.data.model.PokemonSimplifiedResponse
import com.victor.pokedex.data.model.PokemonTypeResponse
import com.victor.pokedex.domain.model.DamageRelations
import com.victor.pokedex.domain.model.GameIndex
import com.victor.pokedex.domain.model.Generation
import com.victor.pokedex.domain.model.Language
import com.victor.pokedex.domain.model.MoveDamageClass
import com.victor.pokedex.domain.model.PastDamageRelations
import com.victor.pokedex.domain.model.PokemonMove
import com.victor.pokedex.domain.model.PokemonName
import com.victor.pokedex.domain.model.PokemonSimplified
import com.victor.pokedex.domain.model.PokemonType

internal fun PokemonTypeResponse.toDomain() =
    PokemonType(
        id = id ?: 0,
        name = name.orEmpty(),
        damageRelations = damageRelations.toDomain(),
        pastDamageRelations = pastDamageRelations.toPastDamageRelationsDomain(),
        gameIndices = gameIndices.toGameIndexDomain(),
        generation = generation.toGenerationDomain(),
        moveDamageClass = moveDamageClass.toMoveDamageDomain(),
        names = names.toPokemonNameDomain(),
        pokemons = pokemon.toPokemonSimplifiedDomain(),
        moves = moves.toPokemonMoveDomain()
    )

private fun DamageRelationsResponse?.toDomain() =
    DamageRelations(
        noDamageTo = this?.noDamageTo.toPokemonTypeSimplifiedDomain(),
        halfDamageTo = this?.halfDamageTo.toPokemonTypeSimplifiedDomain(),
        doubleDamageTo = this?.doubleDamageTo.toPokemonTypeSimplifiedDomain(),
        noDamageFrom = this?.noDamageFrom.toPokemonTypeSimplifiedDomain(),
        halfDamageFrom = this?.halfDamageFrom.toPokemonTypeSimplifiedDomain(),
        doubleDamageFrom = this?.doubleDamageFrom.toPokemonTypeSimplifiedDomain(),
    )

private fun List<PastDamageRelationsResponse>?.toPastDamageRelationsDomain() = this?.map {
    PastDamageRelations(
        damageRelations = it.damageRelations.toDomain(),
        generation = it.generation.toGenerationDomain()
    )
} ?: emptyList()

private fun List<GameIndexResponse>?.toGameIndexDomain() = this?.map {
    GameIndex(index = it.index ?: 0, url = it.url.orEmpty())
} ?: emptyList()

private fun List<PokemonNameResponse>?.toPokemonNameDomain() = this?.map {
    PokemonName(name = it.name.orEmpty(), language = it.language.toLanguageDomain())
} ?: emptyList()

private fun NameAndUrlResponse?.toGenerationDomain() =
    Generation(name = this?.name.orEmpty(), url = this?.url.orEmpty())

private fun NameAndUrlResponse?.toMoveDamageDomain() =
    MoveDamageClass(name = this?.name.orEmpty(), url = this?.url.orEmpty())

private fun NameAndUrlResponse?.toLanguageDomain() =
    Language(name = this?.name.orEmpty(), url = this?.url.orEmpty())

private fun List<PokemonSimplifiedResponse>?.toPokemonSimplifiedDomain() = this?.map {
    PokemonSimplified(name = it.pokemon?.name.orEmpty(), url = it.pokemon?.url.orEmpty(), slot = it.slot ?: 0)
} ?: emptyList()

private fun List<NameAndUrlResponse>?.toPokemonMoveDomain() = this?.map {
    PokemonMove(name = it.name.orEmpty(), url = it.url.orEmpty())
} ?: emptyList()