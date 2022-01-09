package com.victor.pokedex.presentation.ui.types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.tooling.preview.Preview
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.presentation.ui.components.PokemonTypeCard

@ExperimentalFoundationApi
@Composable
fun TypeList(
    types: SnapshotStateList<PokemonType>,
    onTypeClick: (PokemonType) -> Unit
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
            PokemonType(name = "normal", id = 1),
            PokemonType(name = "fighting", id = 2),
            PokemonType(name = "fire", id = 10),
            PokemonType(name = "grass", id = 12),
            PokemonType(name = "water", id = 11),
            PokemonType(name = "dark", id = 17)
        ).toMutableStateList(),
        onTypeClick = { }
    )
}