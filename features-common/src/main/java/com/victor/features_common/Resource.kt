package com.victor.features_common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.victor.features_common.components.EmptyUI
import com.victor.features_common.components.ErrorUI
import com.victor.features_common.components.LoadingUI
import com.victor.networking.PokedexException

sealed class Resource {
    object Empty : Resource()
    object Loading : Resource()
    data class Error(val exception: PokedexException) : Resource()
    data class Success<T>(val data: T) : Resource()
}

@Composable
fun <T> ObserveResource(state: MutableState<Resource>, onRetry: () -> Unit = {}, onSuccess: @Composable (T) -> Unit) {
    when (state.value) {
        is Resource.Empty -> EmptyUI()
        is Resource.Loading -> LoadingUI()
        is Resource.Error -> {
            val exception = state.getAsErrorResource()?.exception ?: PokedexException.UnexpectedException
            ErrorUI(
                message = stringResource(
                    id = ErrorHandler.handleMessage(exception)
                ),
                reload = onRetry
            )
        }
        is Resource.Success<*> -> {
            val resource = state.getAsSuccessResource<T>()?.data
            when {
                resource == null -> state.value = Resource.Error(PokedexException.FormatException)
                resource is List<*> && resource.isEmpty() -> EmptyUI()
                else -> onSuccess.invoke(resource)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> MutableState<Resource>.getAsSuccessResource() = value as? Resource.Success<T>
fun MutableState<Resource>.getAsErrorResource() = value as? Resource.Error