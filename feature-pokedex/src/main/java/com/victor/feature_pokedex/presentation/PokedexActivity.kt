package com.victor.feature_pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.victor.feature_pokedex.presentation.ui.navigation.PokedexNavHost
import com.victor.feature_pokedex.presentation.ui.theme.PokedexTheme
import com.victor.features_common.viewModel
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

@ExperimentalFoundationApi
class PokedexActivity : ComponentActivity(), DIAware {

    override val di by closestDI()
    private val viewModel: PokedexViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexApp(viewModel)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun PokedexApp(viewModel: PokedexViewModel) {
    PokedexTheme {
        val navController = rememberNavController()
        PokedexNavHost(
            navController = navController,
            viewModel = viewModel,
        )
    }
}