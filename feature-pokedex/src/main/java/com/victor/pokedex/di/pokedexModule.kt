package com.victor.pokedex.di

import com.victor.pokedex.data.PokedexGateway
import com.victor.pokedex.data.PokedexInfrastructure
import com.victor.pokedex.domain.service.PokedexService
import com.victor.pokedex.presentation.PokedexViewModel
import org.kodein.di.DI
import org.kodein.di.bind
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