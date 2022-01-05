package com.victor.pokedex

import android.app.Application
import com.victor.networking.networkingModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware {

    override val di = DI {
        import(networkingModule)
    }
}