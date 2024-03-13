package com.victor.feature_pokedex.presentation.ui.utils

import com.victor.feature_pokedex.domain.model.PokemonInformation
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

fun Int?.formatHeightToKg(): String {
    val heightInMeters = (this?.toFloat() ?: 0.0f) / 10
    return DecimalFormat(FLOAT_FORMAT_TEMPLATE).format(heightInMeters)
}

fun Int?.formatWeightToKg(): String {
    val weightInKg = (this?.toFloat() ?: 0.0f) / 10
    return DecimalFormat(FLOAT_FORMAT_TEMPLATE).format(weightInKg)
}

fun PokemonInformation?.formatFlavorText() =
    this?.flavorText
        ?.replace("\n", "")
        ?.replace(
            name?.uppercase(Locale.ROOT).toString(),
            name?.replaceFirstChar { it.titlecase(Locale.getDefault()) }.toString()
        )
        ?.replace(".", ". ")
        ?: ""

fun String?.beautifyString() =
    this?.replace("-", " ")
        ?.replaceFirstChar { it.titlecase(Locale.getDefault()) }
        ?: ""

fun List<String>.formatEggGroups() =
    joinToString(", ") {
        it.capitalize()
    }

fun String.capitalize() =
    replaceFirstChar {
        it.titlecase(Locale.getDefault())
    }

private const val FLOAT_FORMAT_TEMPLATE = "#.#"
private const val FLOAT_TWO_DECIMALS_FORMAT_TEMPLATE = "#.##"