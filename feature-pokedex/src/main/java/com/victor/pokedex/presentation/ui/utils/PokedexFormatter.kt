package com.victor.pokedex.presentation.ui.utils

fun Long.formatPokedexNumber() =
    when (this) {
        in 1..9 -> "#00$this"
        in 10..99 -> "#0$this"
        else -> "#$this"
    }

// There must be a better way
fun String.formatPokemonName() =
    this.split("-")
        .joinToString(" ") {
            it.replaceFirstChar { firstChar ->
                firstChar.titlecase()
            }
        }