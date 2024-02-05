package com.victor.pokedex

import android.app.Application
import com.victor.feature_pokedex.di.pokedexModule
import com.victor.networking.di.networkingModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware {

    override val di = DI {
        import(networkingModule)
        import(pokedexModule)
    }
}