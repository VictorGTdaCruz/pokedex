package com.victor.feature_pokedex.presentation.ui.utils

import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.model.PokemonStat
import java.text.DecimalFormat
import java.util.Locale

fun Long.formatPokedexNumber() =
    when (this) {
        in 1..9 -> "#00$this"
        in 10..99 -> "#0$this"
        else -> "#$this"
    }

fun String.formatPokemonName() =
    split("-")
        .joinToString(" ") {
            it.capitalize()
        }

fun PokemonInformation?.formatFlavorText() =
    this?.flavorText
        ?.replace("\n", "")
        ?.replace(".", ". ")
        ?.replace(name.uppercase(Locale.ROOT), name.capitalize())
        ?: ""

fun List<PokemonStat>.formatEV() =
    this.filter { it.effort > 0 }
        .joinToString(separator = STRING_SEPARATOR) {
            "${it.effort} ${it.name.beautifyString()}"
        }

fun String?.beautifyString() =
    this?.replace("-", " ")
        ?.capitalize()
        ?: ""

fun List<String>.formatEggGroups() =
    joinToString(separator = STRING_SEPARATOR) {
        it.capitalize()
    }

fun String.capitalize() =
    replaceFirstChar {
        it.titlecase(Locale.getDefault())
    }

fun Float.formatPercentage(): String =
    DecimalFormat(FLOAT_FORMAT_TEMPLATE_TWO_DECIMALS).format(this * 100)

private const val STRING_SEPARATOR = ", "
private const val FLOAT_FORMAT_TEMPLATE_ONE_DECIMAL = "#.#"
private const val FLOAT_FORMAT_TEMPLATE_TWO_DECIMALS = "#.##"