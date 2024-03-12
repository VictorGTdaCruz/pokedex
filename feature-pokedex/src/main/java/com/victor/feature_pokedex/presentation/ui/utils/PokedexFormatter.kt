package com.victor.feature_pokedex.presentation.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.victor.feature_pokedex.R
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
            it.replaceFirstChar { firstChar ->
                firstChar.titlecase()
            }
        }

@Composable
fun Int?.formatPokemonHeight(): String {
    val heightInMeters = (this?.toFloat() ?: 0.0f) / 10
    val heightInMetersFormatted = DecimalFormat(FLOAT_FORMAT_TEMPLATE).format(heightInMeters)
    return stringResource(id = R.string.about_tab_height_in_meters, heightInMetersFormatted)
}

@Composable
fun Int?.formatPokemonWeight(): String {
    val weightInKg = (this?.toFloat() ?: 0.0f) / 10
    val weightInKgFormatted = DecimalFormat(FLOAT_FORMAT_TEMPLATE).format(weightInKg)
    return stringResource(id = R.string.about_tab_weight_in_kilograms, weightInKgFormatted)
}

fun String?.formatFlavorText(name: String?) =
    this?.replace("\n", "")
        ?.replace(
            name?.uppercase(Locale.ROOT).toString(),
            name?.replaceFirstChar { it.titlecase(Locale.getDefault()) }.toString()
        )
        ?.replace(".", ". ")

@Composable
fun Int.formatHatchCounter() =
    stringResource(id = R.string.about_tab_egg_cycles_in_steps, 255.times(this + 1).toString())

@Composable
fun Float.formatCatchProbability(): String {
    val probability = times(100)
    val probabilityFormatted = DecimalFormat(FLOAT_TWO_DECIMALS_FORMAT_TEMPLATE).format(probability)
    return stringResource(id = R.string.about_tab_catch_rate_with_pokeball, probabilityFormatted.toString())
}

private const val FLOAT_FORMAT_TEMPLATE = "#.#"
private const val FLOAT_TWO_DECIMALS_FORMAT_TEMPLATE = "#.##"