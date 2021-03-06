package com.victor.networking.di

import com.victor.networking.Constants
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = DI.Module("networking") {

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl(Constants.pokeApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}