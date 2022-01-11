package com.victor.features_common

import androidx.compose.runtime.MutableState
import com.victor.networking.PokedexException

sealed class Resource {
    object Empty : Resource()
    object Loading : Resource()
    data class Error(val exception: PokedexException) : Resource()
    data class Success<T>(val data: T) : Resource()
}

@Suppress("UNCHECKED_CAST")
fun <T> MutableState<Resource>.getAsSuccessResource() = value as? Resource.Success<T>
fun MutableState<Resource>.getAsErrorResource() = value as? Resource.Error