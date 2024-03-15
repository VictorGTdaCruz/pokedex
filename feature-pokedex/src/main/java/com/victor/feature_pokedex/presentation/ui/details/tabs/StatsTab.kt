package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.presentation.ui.components.PokemonTypeIcon
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.beautifyString
import com.victor.feature_pokedex.presentation.ui.utils.formatTypeEffectiveness
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
fun statsTab(pokemonInformation: PokemonInformation) {
    val typeColor = TypeColorHelper.findBackground(pokemonInformation.types.first().type.id)
    Column(
        Modifier.padding(24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.stats_tab_base_stats),
            color = typeColor,
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        StatBaseTable(pokemonInformation = pokemonInformation, typeColor = typeColor)
        Row(
            Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.stats_tab_total),
                color = Color.Black,
                style = PokedexTextStyle.description.bold(),
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(weight = 0.3f)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = pokemonInformation.stats.sumOf { it.baseStat }.toString(),
                style = PokedexTextStyle.body.bold(),
                color = Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.stats_tab_type_defenses),
            color = typeColor,
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(
                id = R.string.stats_tab_type_defenses_description,
                pokemonInformation.name.beautifyString()
            ),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(pokemonInformation.typeDefenses.size / 2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            userScrollEnabled = false,
            modifier = Modifier.height(150.dp),
            content = {
                items(pokemonInformation.typeDefenses.size) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PokemonTypeIcon(
                            type = pokemonInformation.typeDefenses[it].type,
                            iconSize = 16.dp,
                            cardPadding = 0.dp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = pokemonInformation.typeDefenses[it].effectiveness.formatTypeEffectiveness(),
                            style = PokedexTextStyle.body,
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun StatBaseTable(pokemonInformation: PokemonInformation, typeColor: Color) {
    pokemonInformation.stats.forEach {
        StatTabCell(name = it.name.beautifyString(), stat = it.baseStat, typeColor = typeColor)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun StatTabCell(name: String, stat: Int, typeColor: Color) {
    val statLevel = (stat.toFloat() / 255)
    TabCell(
        title = name,
        description = {
            BoxWithConstraints(
                modifier = Modifier
                    .height(4.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .align(CenterVertically)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .width(maxWidth)
                        .fillMaxHeight()
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(statLevel)
                        .clip(RoundedCornerShape(4.dp))
                        .background(typeColor)
                )
            }
            Spacer(modifier = Modifier.width(36.dp))
            Text(
                text = stat.toString(),
                style = PokedexTextStyle.body,
                textAlign = TextAlign.End,
                modifier = Modifier.width(30.dp)
            )
        }
    )
}