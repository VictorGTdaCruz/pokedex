package com.victor.feature_pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.presentation.ui.components.AppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
private fun PokedexApp(viewModel: PokedexViewModel) {
    PokedexTheme {
        val navController = rememberNavController()

        Box(
            Modifier.background(Color.Gray),
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.home_toolbar_background),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )
            Scaffold(
                containerColor = Color.White,
                topBar = {
                    AppBar()
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
}