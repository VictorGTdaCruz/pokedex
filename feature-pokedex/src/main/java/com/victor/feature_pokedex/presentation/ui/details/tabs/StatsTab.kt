package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
fun statsTab(pokemonDetails: PokemonDetails?) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text(
            text = "Base Stats",
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "HP",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Attack",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Defense",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sp. Attack",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sp. Defense",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Speed",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Total",
            color = Color.Black,
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum\nLorem Ipsum Lorem Ipsum\nLorem Ipsum",
            style = PokedexTextStyle.description,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Type Defenses",
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum",
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}