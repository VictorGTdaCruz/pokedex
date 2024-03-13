package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.features_common.components.PokedexTextStyle

@Composable
fun evolutionTab(pokemonInformation: PokemonInformation) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text(
            text = "Evolution Chart",
            color = TypeColorHelper.findBackground(pokemonInformation.types.first().type.id),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Spacer(modifier = Modifier.height(8.dp))
    }
}