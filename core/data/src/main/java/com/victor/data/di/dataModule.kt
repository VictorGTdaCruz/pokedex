package com.victor.data.di

import com.victor.data.repository.PokemonRepository
import com.victor.data.repository.RemotePokemonRepository
import com.victor.data.repository.RemoteTypeRepository
import com.victor.data.repository.TypeRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val dataModule = DI.Module("data") {

    bind<PokemonRepository>() with provider {
        RemotePokemonRepository(instance())
    }

    bind<TypeRepository>() with provider {
        RemoteTypeRepository(instance())
    }
}