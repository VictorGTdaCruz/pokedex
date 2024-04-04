package com.victor.data.mapper

import com.victor.model.Evolution
import com.victor.model.EvolutionChain
import com.victor.model.Pokemon
import com.victor.model.PokemonEvolution
import com.victor.model.PokemonInformation
import com.victor.model.Specie
import com.victor.model.Type
import com.victor.model.TypeEffectiveness
import com.victor.model.TypeSimple

internal object PokemonInformationMapper {

    private const val ONE_HUNDRED_PERCENT = 100f
    private const val PERCENT_IN_EIGHTHS = ONE_HUNDRED_PERCENT / 8
    private const val TEN = 10f
    private const val TWO = 2f
    private const val WATER = "water"
    private const val WATER1 = "water1"

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
    private fun calculateFemaleRate(genderRate: Int) =
        when (genderRate) {
            -1 -> null
            else -> genderRate * PERCENT_IN_EIGHTHS
        }

    private fun calculateTypeDefensesEffectiveness(
        typeList: List<TypeSimple>,
        typeListOfCurrentPokemon: List<Type>
    ) =
        mutableListOf<TypeEffectiveness>().apply {
            val firstType = typeListOfCurrentPokemon.firstOrNull()
            val secondType = typeListOfCurrentPokemon.lastOrNull()
            typeList.forEach {
                var effectiveness = 1f
                effectiveness *= firstType?.defenseEffectivenessAgainst(it) ?: 1f
                effectiveness *= secondType?.defenseEffectivenessAgainst(it) ?: 1f
                add(TypeEffectiveness(type = it, effectiveness = effectiveness))
            }
        }

    private fun Type.defenseEffectivenessAgainst(currentType: TypeSimple) =
        damageRelations.run {
            when {
                doubleDamageFrom.find { it.id == currentType.id } != null -> 2.0f
                halfDamageFrom.find { it.id == currentType.id } != null -> 0.5f
                noDamageFrom.find { it.id == currentType.id } != null -> 0f
                else -> 1f
            }
        }

    private fun getPokemonEvolutionList(
        response: EvolutionChain?,
        pokemonList: List<Pokemon>
    ): List<PokemonEvolution> =
        if (response?.evolvesTo.isNullOrEmpty()) {
            mutableListOf()
        } else {
            val currentId = response?.specieId
            val nextIds = response?.evolvesTo?.map { it.specieId }
            mutableListOf<PokemonEvolution>().apply {
                add(
                    PokemonEvolution(
                        from = pokemonList.filter { it.id == currentId },
                        to = pokemonList.filter { nextIds?.contains(it.id) == true },
                        trigger = response?.evolvesTo?.firstOrNull()?.evolutionDetails?.firstOrNull()?.trigger,
                        minLevel = response?.evolvesTo?.firstOrNull()?.evolutionDetails?.firstOrNull()?.minLevel
                    )
                )
                response?.evolvesTo?.forEach {
                    addAll(getPokemonEvolutionList(it, pokemonList))
                }
            }
        }

    fun map(
        pokemon: Pokemon,
        specie: Specie,
        typeList: List<TypeSimple>,
        typeListOfCurrentPokemon: List<Type>,
        evolutionChain: Evolution,
        pokemonListFromEvolutionChain: List<Pokemon>
    ): PokemonInformation {
        val captureProbability =
            calculateCaptureProbability(captureRate = specie.captureRate)
        val hatchSteps = calculateHatchSteps(hatchCounter = specie.hatchCounter)

        val femaleRate = calculateFemaleRate(genderRate = specie.genderRate)
        val maleRate = femaleRate?.let { ONE_HUNDRED_PERCENT - it }

        val eggGroups = specie.eggGroups.map { it.replace(WATER1, WATER) }

        val heightInKg = pokemon.height / TEN
        val weightInKg = pokemon.weight / TEN

        val typeDefenses = calculateTypeDefensesEffectiveness(typeList, typeListOfCurrentPokemon)
        val weaknesses = typeDefenses
            .filter { it.effectiveness >= TWO }
            .map { it.type }

        val evolutions = getPokemonEvolutionList(evolutionChain.chain, pokemonListFromEvolutionChain)

        return PokemonInformation(
            id = pokemon.id,
            name = pokemon.name,
            height = heightInKg,
            weight = weightInKg,
            typeList = pokemon.typeList,
            stats = pokemon.statList,
            sprite = pokemon.sprite,
            abilityList = pokemon.abilityList,
            baseXp = pokemon.baseXp,
            captureRate = specie.captureRate,
            captureProbability = captureProbability,
            growthRate = specie.growthRate,
            flavorText = specie.flavorText,
            genera = specie.genera,
            maleRate = maleRate,
            femaleRate = femaleRate,
            eggGroupList = eggGroups,
            hatchCounter = specie.hatchCounter,
            hatchSteps = hatchSteps,
            typeDefenseList = typeDefenses,
            weaknessList = weaknesses,
            evolutionList = evolutions,
        )
    }
}