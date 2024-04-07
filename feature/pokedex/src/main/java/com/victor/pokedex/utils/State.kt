package com.victor.pokedex.utils

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.victor.network.PokedexException
import com.victor.pokedex.components.EmptyUI
import com.victor.pokedex.components.ErrorUI
import com.victor.pokedex.components.LoadingUI

sealed class State {
    object Empty : State()
    object Loading : State()
    data class Error(val exception: PokedexException) : State()
    data class Success<T>(val data: T) : State()
}

@Composable
fun <T> observeState(
    state: MutableState<State>,
    onRetry: () -> Unit = {},
    onSuccess: @Composable (T) -> Unit,
) {
    when (state.value) {
        is State.Empty -> EmptyUI()
        is State.Loading -> LoadingUI()
        is State.Error -> {
            val exception = state.getAsErrorState()?.exception ?: PokedexException.UnexpectedException()
            ErrorUI(
                message = stringResource(
                    id = ErrorHandler.handleMessage(exception)
                ),
                reload = onRetry
            )
        }
        is State.Success<*> -> {
            val resource = state.getAsSuccessState<T>()?.data
            when {
                resource == null -> state.value = State.Error(PokedexException.FormatException())
                resource is List<*> && resource.isEmpty() -> state.value = State.Empty
                else -> onSuccess(resource)
            }
        }
    }
}

fun <T> LazyListScope.observeStateInsideLazyList(
    state: MutableState<State>,
    onRetry: () -> Unit = {},
    onSuccess: LazyListScope.(T) -> Unit,
) {
    when (state.value) {
        is State.Empty -> item { EmptyUI() }
        is State.Loading -> item { LoadingUI() }
        is State.Error -> {
            val exception = state.getAsErrorState()?.exception ?: PokedexException.UnexpectedException()
            item {
                ErrorUI(
                    message = stringResource(
                        id = ErrorHandler.handleMessage(exception)
                    ),
                    reload = onRetry
                )
            }
        }
        is State.Success<*> -> {
            val resource = state.getAsSuccessState<T>()?.data
            when {
                resource == null -> state.value = State.Error(PokedexException.FormatException())
                resource is List<*> && resource.isEmpty() -> state.value = State.Empty
                else -> onSuccess(resource)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> MutableState<State>.getAsSuccessState() = value as? State.Success<T>
fun MutableState<State>.getAsErrorState() = value as? State.Error