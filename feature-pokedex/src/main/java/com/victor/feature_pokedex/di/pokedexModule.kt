package com.victor.feature_pokedex.di

import com.victor.feature_pokedex.data.PokedexGateway
import com.victor.feature_pokedex.data.PokedexInfrastructure
import com.victor.feature_pokedex.domain.PokedexUseCase
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.feature_pokedex.presentation.PokedexViewModel
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

    bind<PokedexUseCase>() with provider {
        PokedexUseCase(instance())
    }

    bind<PokedexViewModel>() with provider {
        PokedexViewModel(instance())
    }
}