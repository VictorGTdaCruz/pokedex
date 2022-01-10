package com.victor.networking

sealed class PokedexException: RuntimeException() {
    object ConnectionException: PokedexException()
    object UnexpectedException: PokedexException()
}