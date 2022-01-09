package com.victor.pokedex.presentation.ui.types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.victor.pokedex.R
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.components.Loading
import com.victor.pokedex.presentation.ui.theme.Background

@ExperimentalFoundationApi
@Composable
internal fun PokemonTypesScreenBody(
    viewModel: PokedexViewModel,
    onTypeClick: (PokemonType) -> Unit
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.toolbarTitle = stringResource(id = R.string.type_screen_title)
        if (viewModel.isLoading)
            Loading()
        else
            TypeListScreenContent(viewModel.pokemonTypes, onTypeClick)

        LaunchedEffect(Unit) {
            viewModel.loadPokemonTypes()
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun TypeListScreenContent(
    types: SnapshotStateList<PokemonType>,
    onTypeClick: (PokemonType) -> Unit,
) {
    TypeList(types = types) { onTypeClick(it) }
}