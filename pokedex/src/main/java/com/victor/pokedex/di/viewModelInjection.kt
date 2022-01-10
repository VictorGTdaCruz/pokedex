package com.victor.pokedex.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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