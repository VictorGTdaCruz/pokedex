package com.victor.pokedex.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.victor.pokedex.domain.model.PokemonTypeSimplified

@ExperimentalFoundationApi
@Composable
fun TypeListScreen(
    types: SnapshotStateList<PokemonTypeSimplified>,
    onTypeClick: (PokemonTypeSimplified) -> Unit,
    isLoading: Boolean
) {
    Surface(color = MaterialTheme.colors.background) {
        if (isLoading)
            Loading()
        else
            TypeListScreenContent(types, onTypeClick)
    }
}

@ExperimentalFoundationApi
@Composable
private fun TypeListScreenContent(
    types: SnapshotStateList<PokemonTypeSimplified>,
    onTypeClick: (PokemonTypeSimplified) -> Unit,
) {
    Column {
        Text(text = "Choose your type!")
        TypeList(types = types) { onTypeClick(it) }
    }
}