package com.victor.pokedex.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victor.pokedex.data.PokedexGateway
import com.victor.pokedex.data.PokedexInfrastructure
import com.victor.pokedex.domain.service.PokedexService
import com.victor.pokedex.presentation.PokedexViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import retrofit2.Retrofit

val pokedexModule = DI.Module("pokedex") {

    bind<PokedexGateway>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(PokedexGateway::class.java)
    }

    bind<PokedexService>() with provider {
        PokedexInfrastructure(instance())
    }

    bind<PokedexViewModel>() with provider {
        PokedexViewModel(instance())
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel, T> T.viewModel() where T: DIAware, T: ComponentActivity = lazy {

    val factory = object : ViewModelProvider.Factory {
        override fun <Model : ViewModel> create(klass: Class<Model>) =
            direct.instance<VM>() as Model
    }

    ViewModelProvider(this, factory).get(VM::class.java)
}