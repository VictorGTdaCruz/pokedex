package com.victor.feature_pokedex.di

import com.victor.feature_pokedex.presentation.ui.details.DetailsViewModel
import com.victor.feature_pokedex.presentation.ui.home.HomeViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

val pokedexModule = DI.Module("pokedex") {

    bind<HomeViewModel>() with provider {
        HomeViewModel(instance(), instance(), instance())
    }

    bind<DetailsViewModel>() with provider {
        DetailsViewModel(instance())
    }
}