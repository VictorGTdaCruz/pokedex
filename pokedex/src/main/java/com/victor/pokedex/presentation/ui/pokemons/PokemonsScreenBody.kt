package com.victor.pokedex.presentation.ui.pokemons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.victor.pokedex.data.model.PokemonSimplifiedResponse
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.theme.Background

@Composable
internal fun PokemonsScreenBody(
    viewModel: PokedexViewModel,
    pokemonTypeId: Long,
    onPokemonClick: (PokemonSimplifiedResponse) -> Unit
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            content = {
                items(0) {

                }
            }
        )
    }
}