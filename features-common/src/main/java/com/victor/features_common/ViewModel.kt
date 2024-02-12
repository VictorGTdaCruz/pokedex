package com.victor.features_common

import androidx.activity.ComponentActivity
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.victor.networking.PokedexException
import kotlinx.coroutines.launch
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel, T> T.viewModel()
        where T : DIAware,
              T : ComponentActivity =

    lazy {
        val factory = object : ViewModelProvider.Factory {
            override fun <Model : ViewModel> create(klass: Class<Model>) =
                direct.instance<VM>() as Model
        }

        ViewModelProvider(this, factory).get(VM::class.java)
    }

fun <T> ViewModel.manageStateDuringRequest(
    mutableState: MutableState<State>,
    onSuccess: (T) -> Unit = {},
    request: suspend () -> T,
) {
    viewModelScope.launch {
        mutableState.value = State.Loading
        runCatching {
            val response = request()
            if (response is Collection<*> && response.isEmpty()) {
                mutableState.value = State.Empty
            } else {
                mutableState.value = State.Success(response)
                onSuccess.invoke(response)
            }
        }.getOrElse {
            mutableState.value = State.Error(it as PokedexException)
        }
    }
}