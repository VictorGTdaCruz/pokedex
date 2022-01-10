package com.victor.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.victor.pokedex.di.viewModel
import com.victor.pokedex.presentation.ui.components.AppBar
import com.victor.pokedex.presentation.ui.navigation.PokedexNavHost
import com.victor.pokedex.presentation.ui.theme.PokedexTheme
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

        Scaffold(
            topBar = {
                AppBar(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        ) {
            PokedexNavHost(
                navController = navController,
                viewModel = viewModel,
                modifier = Modifier.padding(it)
            )
        }
    }
}