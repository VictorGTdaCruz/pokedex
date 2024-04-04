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
import com.example.model.TypeSimple
import com.victor.feature_pokedex.presentation.ui.utils.beautifyString
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
internal fun PokemonColumn(
    id: Int,
    name: String,
    typeList: List<com.example.model.TypeSimple>,
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
            typeList.forEach {
                Box(
                    modifier = Modifier.padding(end = 6.dp, top = 4.dp)
                ) {
                    PokemonTypeBadge(
                        type = com.example.model.TypeSimple(
                            id = it.id,
                            name = it.name
                        ),
                        iconSize = 14.dp,
                    )
                }
            }
        }
    }
}