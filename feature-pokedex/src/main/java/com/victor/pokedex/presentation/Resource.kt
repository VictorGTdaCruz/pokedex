package com.victor.pokedex.presentation

sealed class Resource {
    object Empty: Resource()
    object Loading: Resource()
    data class Error(val message: String): Resource()
    data class Success<T>(val data: T): Resource()
}