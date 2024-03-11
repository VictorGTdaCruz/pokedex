package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
fun aboutTab(pokemonDetails: PokemonDetails?) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text(
            text = "Lorem Ipsum Lorem Ipsum Lorem Ipsum\nLorem Ipsum Lorem Ipsum\nLorem Ipsum",
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Pokedex Data",
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Species",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Height",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Weight",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Abilities",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Weakness",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Training",
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "EV Yield",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Catch Rate",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Base Friendship",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Base Exp",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Growth Rate",
            style = PokedexTextStyle.description.bold(),
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}