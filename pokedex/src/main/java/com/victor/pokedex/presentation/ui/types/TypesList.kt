package com.victor.pokedex.presentation.ui.types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import com.victor.pokedex.domain.model.PokemonTypeSimplified
import com.victor.pokedex.presentation.ui.components.PokemonTypeCard

@ExperimentalFoundationApi
@Composable
fun TypeList(
    types: SnapshotStateList<PokemonTypeSimplified>,
    onTypeClick: (PokemonTypeSimplified) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(types) {
                PokemonTypeCard(type = it, onTypeClick = onTypeClick)
            }
        }
    )
}



@ExperimentalFoundationApi
@Preview
@Composable
fun Preview() {
    TypeList(
        types = listOf(
            PokemonTypeSimplified(name = "normal", id = 1),
            PokemonTypeSimplified(name = "fighting", id = 2),
            PokemonTypeSimplified(name = "fire", id = 10),
            PokemonTypeSimplified(name = "grass", id = 12),
            PokemonTypeSimplified(name = "water", id = 11),
            PokemonTypeSimplified(name = "dark", id = 17)
        ).toMutableStateList(),
        onTypeClick = { }
    )
}