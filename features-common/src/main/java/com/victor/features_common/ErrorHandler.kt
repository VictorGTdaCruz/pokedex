package com.victor.features_common

import com.victor.networking.PokedexException

object ErrorHandler {

    fun handleMessage(exception: PokedexException) =
        when (exception) {
            is PokedexException.ConnectionException -> R.string.error_connection_exception
            is PokedexException.UnexpectedException -> R.string.error_unknown_exception
        }
}