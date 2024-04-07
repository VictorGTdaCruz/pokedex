package com.victor.domain.di

import com.victor.domain.PokemonInformationUseCase
import com.victor.domain.PokemonListUseCase
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val domainModule = DI.Module("domain") {

    bind<PokemonListUseCase>() with provider {
        PokemonListUseCase(instance(), instance())
    }

    bind<PokemonInformationUseCase>() with provider {
        PokemonInformationUseCase(instance(), instance())
    }
}