package com.victor.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.victor.pokedex.di.viewModel
import com.victor.pokedex.presentation.ui.TypeListScreen
import com.victor.pokedex.presentation.ui.theme.PokedexTheme
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI

class PokedexActivity : ComponentActivity(), DIAware {

    override val di by closestDI()
    private val viewModel: PokedexViewModel by viewModel()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadPokemonTypes()

        setContent {
            PokedexTheme {
                TypeListScreen(
                    types = viewModel.pokemonTypes,
                    onTypeClick = {  },
                    isLoading = viewModel.isLoading.value
                )
            }
        }
    }
}