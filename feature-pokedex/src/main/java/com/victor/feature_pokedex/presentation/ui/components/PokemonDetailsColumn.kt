package com.victor.feature_pokedex.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonName
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
internal fun PokemonDetailsColumn(
    pokemonDetails: PokemonDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = pokemonDetails.id.formatPokedexNumber(),
            style = PokedexTextStyle.description.bold(),
            color = Color.DarkGray,
        )
        Text(
            text = pokemonDetails.name.formatPokemonName(),
            style = PokedexTextStyle.subtitle.bold(),
            color = Color.White,
        )
        Row {
            pokemonDetails.types.forEach {
                Box(
                    modifier = Modifier.padding(end = 6.dp, top = 4.dp)
                ) {
                    PokemonTypeBadge(
                        type = PokemonType(
                            id = it.type.id,
                            name = it.type.name
                        ),
                        iconSize = 14.dp,
                    )
                }
            }
        }
    }
}