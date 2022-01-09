package com.victor.pokedex.presentation.ui.pokemons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.pokedex.R
import com.victor.pokedex.domain.model.PokemonSimplified
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.components.Loading
import com.victor.pokedex.presentation.ui.theme.Background
import com.victor.pokedex.presentation.ui.theme.Grass
import com.victor.pokedex.presentation.ui.utils.TypeColorHelper

@Composable
internal fun PokemonsScreenBody(
    viewModel: PokedexViewModel,
    pokemonTypeId: Long,
    pokemonTypeName: String,
    onPokemonClick: (PokemonSimplified) -> Unit
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.toolbarTitle = stringResource(
            id = R.string.pokemons_screen_title,
            formatArgs = arrayOf(pokemonTypeName.replaceFirstChar { it.titlecase() })
        )
        if (viewModel.isLoading)
            Loading()
        else
            LazyColumn {
                items(viewModel.pokemons) {
                    PokemonCard(it, pokemonTypeId, onPokemonClick)
                }
            }

        LaunchedEffect(Unit) {
            viewModel.loadPokemonsFromType(pokemonTypeId)
        }
    }
}

@Composable
internal fun PokemonCard(
    pokemon: PokemonSimplified,
    pokemonTypeId: Long,
    onPokemonClick: (PokemonSimplified) -> Unit
) {
    Card(
        backgroundColor = TypeColorHelper.find(pokemonTypeId),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onPokemonClick(pokemon) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = pokemon.name.replaceFirstChar { it.titlecase() },
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
internal fun Preview() {
    val pokemonList = listOf(
        PokemonSimplified(name = "Pichu", id = 0, slot = 0),
        PokemonSimplified(name = "Pikachu", id = 0, slot = 0),
        PokemonSimplified(name = "Raichu", id = 0, slot = 0),
    )

    LazyColumn {
        items(pokemonList) {
            PokemonCard(pokemon = it, pokemonTypeId = 13, onPokemonClick = { })
        }
    }
}