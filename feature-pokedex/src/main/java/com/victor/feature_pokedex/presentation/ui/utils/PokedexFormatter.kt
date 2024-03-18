package com.victor.feature_pokedex.presentation.ui.utils

import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.domain.model.Stat
import java.util.Locale
import kotlin.math.floor
import kotlin.math.roundToInt

fun Long.formatPokedexNumber() =
    when (this) {
        in 1..9 -> "#00$this"
        in 10..99 -> "#0$this"
        else -> "#$this"
    }

fun Float.formatKgToLb() = times(KG_TO_LB_RATE)

fun Float.formatMToFeetAndInches(): Pair<Int, Int> {
    val valueInCM = this * 100
    val feet = floor(valueInCM / FEET_TO_CM_RATE).toInt()
    val inches = (valueInCM / INCHES_TO_CM_RATE - feet * 12).roundToInt()
    return feet to inches
}

fun PokemonInformation?.formatFlavorText() =
    this?.flavorText
        ?.replace("\n", " ")
        ?.replace("\u000c", " ")
        ?.replace(".", ". ")
        ?.replace("  ", " ")
        ?.replace("POKéMON", "pokémon")
        ?.replace(name.uppercase(Locale.ROOT), name.capitalize())
        ?: ""

fun List<Stat>.formatEV() =
    this.filter { it.effort > 0 }
        .joinToString(separator = STRING_SEPARATOR) {
            "${it.effort} ${it.name.beautifyString()}"
        }

fun String?.beautifyString() =
    this?.replace("-", " ")
        ?.capitalize()
        ?: ""

fun String.capitalize() =
    replaceFirstChar {
        it.titlecase(Locale.getDefault())
    }

fun List<String>.formatEggGroups() =
    joinToString(separator = STRING_SEPARATOR) {
        it.capitalize()
    }
        .beautifyString()

fun Float.formatPercentage(): String =
    String.format(Locale.getDefault(), FLOAT_FORMAT_TEMPLATE_TWO_DECIMALS, this * 100)

fun Double.formatDoubleToString(): String =
    String.format(Locale.getDefault(), FLOAT_FORMAT_TEMPLATE_ONE_DECIMAL, this)

fun Int.formatIntToString(): String =
    String.format(Locale.getDefault(), FLOAT_FORMAT_TEMPLATE_NO_DECIMAL, this)

fun Float.beautifyFloatToString(): String =
    if (this % 1 == 0f)
        toInt().toString()
    else
        toString()

fun Double.formatTypeEffectiveness() =
    when {
        this == 0.0 -> "0"
        this == 0.25 -> "1/4"
        this == 0.5 -> "1/2"
        this == 2.0 -> "2"
        this == 4.0 -> "4"
        else -> ""
    }

private const val STRING_SEPARATOR = ", "
private const val FLOAT_FORMAT_TEMPLATE_NO_DECIMAL = "%,1d"
private const val FLOAT_FORMAT_TEMPLATE_ONE_DECIMAL = "%,.1f"
private const val FLOAT_FORMAT_TEMPLATE_TWO_DECIMALS = "%,.2f"
private const val KG_TO_LB_RATE = 2.20462
private const val FEET_TO_CM_RATE = 30.48
private const val INCHES_TO_CM_RATE = 2.54