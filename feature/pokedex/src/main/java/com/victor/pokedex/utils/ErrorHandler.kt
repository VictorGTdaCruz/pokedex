package com.victor.pokedex.utils


import com.victor.network.PokedexException
import com.victor.pokedex.R

object ErrorHandler {

    fun handleMessage(exception: PokedexException) =
        when (exception) {
            is PokedexException.ConnectionException -> R.string.error_connection_exception
            is PokedexException.FormatException -> R.string.error_format_exception
            is PokedexException.UnexpectedException -> R.string.error_unknown_exception
        }
}