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

fun <T> ViewModel.manageResourcesDuring(
    mutableResource: MutableState<Resource>,
    request: suspend () -> T
) {
    viewModelScope.launch {
        mutableResource.value = Resource.Loading
        runCatching {
            val response = request()
            if (response is Collection<*> && response.isEmpty())
                mutableResource.value = Resource.Empty
            else
                mutableResource.value = Resource.Success(response)
        }.getOrElse {
            mutableResource.value = Resource.Error(it as PokedexException)
        }
    }
}