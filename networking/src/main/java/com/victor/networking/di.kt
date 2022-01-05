package com.victor.networking

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import retrofit2.Retrofit

val networkingModule = DI.Module("networking") {

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl("")
            .build()
    }
}