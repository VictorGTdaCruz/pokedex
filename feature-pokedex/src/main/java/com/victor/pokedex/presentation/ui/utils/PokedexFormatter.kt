package com.victor.pokedex.presentation.ui.utils

import java.text.DecimalFormat

fun Long.formatPokedexNumber() =
    when (this) {
        in 1..9 -> "#00$this"
        in 10..99 -> "#0$this"
        else -> "#$this"
    }

fun String.formatPokemonName() =
    split("-")
        .joinToString(" ") {
            it.replaceFirstChar { firstChar ->
                firstChar.titlecase()
            }
        }

fun Int.formatPokemonHeight(): String {
    val heightInMeters = toFloat() / 10
    val heightInMetersFormatted = formatter.format(heightInMeters)
    return "$heightInMetersFormatted m"
}

fun Int.formatPokemonWeight(): String {
    val weightInKg = toFloat() / 10
    val weightInKgFormatted = formatter.format(weightInKg)
    return "$weightInKgFormatted kg"
}

private const val FLOAT_FORMAT_TEMPLATE = "#.#"
private val formatter = DecimalFormat(FLOAT_FORMAT_TEMPLATE)