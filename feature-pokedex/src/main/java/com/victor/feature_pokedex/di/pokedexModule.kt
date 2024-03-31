package com.victor.feature_pokedex.di

import com.victor.feature_pokedex.data.PokedexDataSource
import com.victor.feature_pokedex.data.PokemonRepositoryImpl
import com.victor.feature_pokedex.data.TypeRepositoryImpl
import com.victor.feature_pokedex.domain.PokemonInformationUseCase
import com.victor.feature_pokedex.domain.PokemonListUseCase
import com.victor.feature_pokedex.domain.service.PokemonRepository
import com.victor.feature_pokedex.domain.service.TypeRepository
import com.victor.feature_pokedex.presentation.PokedexViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import retrofit2.Retrofit

val pokedexModule = DI.Module("pokedex") {

    bind<PokedexDataSource>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(PokedexDataSource::class.java)
    }

    bind<PokemonRepository>() with provider {
        PokemonRepositoryImpl(instance())
    }

    bind<TypeRepository>() with provider {
        TypeRepositoryImpl(instance())
    }

    bind<PokemonListUseCase>() with provider {
        PokemonListUseCase(instance(), instance())
    }

    bind<PokemonInformationUseCase>() with provider {
        PokemonInformationUseCase(instance(), instance())
    }

    bind<PokedexViewModel>() with provider {
        PokedexViewModel(instance(), instance(), instance(), instance())
    }
}