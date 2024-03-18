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
import com.victor.feature_pokedex.domain.model.TypeSimple
import com.victor.feature_pokedex.domain.model.PokemonTypeWithSlot
import com.victor.feature_pokedex.presentation.ui.utils.beautifyString
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
internal fun PokemonColumn(
    id: Long,
    name: String,
    types: List<PokemonTypeWithSlot>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = id.formatPokedexNumber(),
            style = PokedexTextStyle.description.bold(),
            color = Color.DarkGray,
        )
        Text(
            text = name.beautifyString(),
            style = PokedexTextStyle.subtitle.bold(),
            color = Color.White,
        )
        Row {
            types.forEach {
                Box(
                    modifier = Modifier.padding(end = 6.dp, top = 4.dp)
                ) {
                    PokemonTypeBadge(
                        type = TypeSimple(
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