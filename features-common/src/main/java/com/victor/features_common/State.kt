package com.victor.features_common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.victor.features_common.components.EmptyUI
import com.victor.features_common.components.ErrorUI
import com.victor.features_common.components.LoadingUI
import com.victor.networking.PokedexException

sealed class State {
    object Empty : State()
    object Loading : State()
    data class Error(val exception: PokedexException) : State()
    data class Success<T>(val data: T) : State()
}

@Composable
fun <T> ObserveState(state: MutableState<State>, onRetry: () -> Unit = {}, onSuccess: @Composable (T) -> Unit) {
    when (state.value) {
        is State.Empty -> EmptyUI()
        is State.Loading -> LoadingUI()
        is State.Error -> {
            val exception = state.getAsErrorResource()?.exception ?: PokedexException.UnexpectedException()
            ErrorUI(
                message = stringResource(
                    id = ErrorHandler.handleMessage(exception)
                ),
                reload = onRetry
            )
        }
        is State.Success<*> -> {
            val resource = state.getAsSuccessResource<T>()?.data
            when {
                resource == null -> state.value = State.Error(PokedexException.FormatException())
                resource is List<*> && resource.isEmpty() -> EmptyUI()
                else -> onSuccess.invoke(resource)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> MutableState<State>.getAsSuccessResource() = value as? State.Success<T>
fun MutableState<State>.getAsErrorResource() = value as? State.Error