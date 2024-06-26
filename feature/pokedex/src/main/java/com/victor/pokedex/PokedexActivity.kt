package com.victor.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.victor.pokedex.details.DetailsViewModel
import com.victor.pokedex.home.HomeViewModel
import com.victor.pokedex.navigation.PokedexNavHost
import com.victor.pokedex.theme.PokedexTheme
import com.victor.pokedex.utils.viewModel
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

@ExperimentalFoundationApi
class PokedexActivity : ComponentActivity(), DIAware {

    override val di by closestDI()
    private val homeViewModel: HomeViewModel by viewModel()
    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexApp(homeViewModel, detailsViewModel)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun PokedexApp(homeViewModel: HomeViewModel, detailsViewModel: DetailsViewModel) {
    PokedexTheme {
        val navController = rememberNavController()
        PokedexNavHost(
            navController = navController,
            homeViewModel = homeViewModel,
            detailsViewModel = detailsViewModel,
        )
    }
}