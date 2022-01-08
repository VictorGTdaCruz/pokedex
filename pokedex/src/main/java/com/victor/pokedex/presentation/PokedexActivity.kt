package com.victor.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.victor.pokedex.di.viewModel
import com.victor.pokedex.presentation.ui.TypeList
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
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Text(text = "Choose your type!")
                        TypeList(viewModel.pokemonTypes) { }
                    }
                }
            }
        }
    }
}