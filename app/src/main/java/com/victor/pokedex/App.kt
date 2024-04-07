package com.victor.pokedex

import android.app.Application
import com.victor.data.di.dataModule
import com.victor.domain.di.domainModule
import com.victor.feature_pokedex.di.pokedexModule
import com.victor.network.di.networkModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware {

    override val di = DI {
        import(networkModule)
        import(dataModule)
        import(domainModule)
        import(pokedexModule)
    }
}