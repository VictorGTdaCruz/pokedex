package com.victor.pokedex.presentation.ui.types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.pokedex.domain.model.PokemonTypeSimplified
import com.victor.pokedex.presentation.TypeColorHelper
import com.victor.pokedex.presentation.TypeDrawableHelper

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
                TypeCard(type = it, onTypeClick = onTypeClick)
            }
        }
    )
}

@Composable
fun TypeCard(
    type: PokemonTypeSimplified,
    onTypeClick: (PokemonTypeSimplified) -> Unit
) {
    Card(
        backgroundColor = TypeColorHelper.find(type.id),
        modifier = Modifier
        .padding(8.dp)
        .clickable { onTypeClick(type) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            val drawableId = TypeDrawableHelper.find(type.id)
            Image(
                painter = painterResource(id = drawableId),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "type_bug",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = type.name,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }
    }
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
            PokemonTypeSimplified(name = "water", id = 11)
        ).toMutableStateList(),
        onTypeClick = { }
    )
}