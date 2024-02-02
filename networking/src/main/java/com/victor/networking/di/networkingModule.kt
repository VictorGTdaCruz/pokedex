package com.victor.networking.di

import com.victor.networking.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level
import java.util.logging.Logger

val networkingModule = DI.Module("networking") {

    bind<Retrofit>() with singleton {
        val logger = HttpLoggingInterceptor {
            val isRequestLog = it.startsWith("-->")
            val isResponseLog = it.startsWith("<--")
            val isNotEndLog = it.contains("END").not()
            if ((isRequestLog || isResponseLog) && isNotEndLog) {
                Logger.getLogger("Pokedex Request").log(Level.INFO, it)
            }
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient
            .Builder()
            .addInterceptor(logger)
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.pokeApiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}