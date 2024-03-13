package com.victor.feature_pokedex.data.mapper

import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.model.PokemonSpecies

internal object PokemonInformationMapper {

    private const val ONE_HUNDRED_PERCENT = 100.0
    private const val PERCENT_IN_EIGHTHS = ONE_HUNDRED_PERCENT / 8
    private const val TEN = 10f

    /**
     * For more information about F and how to calculate capture probability:
     * https://bulbapedia.bulbagarden.net/wiki/Catch_rate
     */
    private fun calculateCaptureProbability(captureRate: Int): Float {
        val f = (255 * 4) / 12
        return (((captureRate + 1f) * (f + 1)) / ((255 + 1) * 256))
    }

    /**
     * Following the formula from api docs:
     * https://pokeapi.co/docs/v2#pokemon-species
     */
    private fun calculateHatchSteps(hatchCounter: Int) = 255 * (hatchCounter + 1)

    /**
     * Following the formula from api docs:
     * https://pokeapi.co/docs/v2#pokemon-species
     */
    private fun calculateFemaleRate(genderRate: Int) = genderRate * PERCENT_IN_EIGHTHS

    fun map(pokemon: Pokemon, pokemonSpecies: PokemonSpecies): PokemonInformation {
        val captureProbability = calculateCaptureProbability(captureRate = pokemonSpecies.captureRate)

        val hatchSteps = calculateHatchSteps(hatchCounter = pokemonSpecies.hatchCounter)

        val femaleRate = calculateFemaleRate(genderRate = pokemonSpecies.genderRate)
        val maleRate = ONE_HUNDRED_PERCENT - femaleRate

        val heightInKg = pokemon.height / TEN
        val weightInKg = pokemon.weight / TEN

        return PokemonInformation(
            id = pokemon.id,
            name = pokemon.name,
            height = heightInKg,
            weight = weightInKg,
            types = pokemon.types,
            stats = pokemon.stats,
            sprites = pokemon.sprites,
            abilities = pokemon.abilities,
            baseXp = pokemon.baseXp,
            captureRate = pokemonSpecies.captureRate,
            captureProbability = captureProbability,
            growthRate = pokemonSpecies.growthRate,
            flavorText = pokemonSpecies.flavorText,
            genera = pokemonSpecies.genera,
            maleRate = maleRate,
            femaleRate = femaleRate,
            eggGroups = pokemonSpecies.eggGroups,
            hatchCounter = pokemonSpecies.hatchCounter,
            hatchSteps = hatchSteps
        )
    }
}