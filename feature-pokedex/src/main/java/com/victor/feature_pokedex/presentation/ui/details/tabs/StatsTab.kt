package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
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
        TabCellStat(title = stringResource(id = R.string.stats_tab_hp), statKey = "hp", pokemonInformation = pokemonInformation)
        Spacer(modifier = Modifier.height(12.dp))
        TabCellStat(title = stringResource(id = R.string.stats_tab_attack), statKey = "attack", pokemonInformation = pokemonInformation)
        Spacer(modifier = Modifier.height(12.dp))
        TabCellStat(title = stringResource(id = R.string.stats_tab_defense), statKey = "defense", pokemonInformation = pokemonInformation)
        Spacer(modifier = Modifier.height(12.dp))
        TabCellStat(title = stringResource(id = R.string.stats_tab_sp_attack), statKey = "special-attack", pokemonInformation = pokemonInformation)
        Spacer(modifier = Modifier.height(12.dp))
        TabCellStat(title = stringResource(id = R.string.stats_tab_sp_defense), statKey = "special-defense", pokemonInformation = pokemonInformation)
        Spacer(modifier = Modifier.height(12.dp))
        TabCellStat(title = stringResource(id = R.string.stats_tab_speed), statKey = "speed", pokemonInformation = pokemonInformation)
        Spacer(modifier = Modifier.height(12.dp))
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
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.stats_tab_type_defenses),
            color = typeColor,
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.stats_tab_type_defenses_description),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun TabCellStat(title: String, statKey: String, pokemonInformation: PokemonInformation?) {
    val stat = pokemonInformation?.stats?.find { it.name == statKey }?.baseStat ?: 0
    val statLevel = (stat.toFloat() / 255)
    TabCell(title, description = {
        Row(
            modifier = Modifier
                .weight(0.8f)
        ) {
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
                    .align(CenterVertically)
            ) {
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .fillMaxWidth(statLevel)
                        .clip(RoundedCornerShape(4.dp))
                        .background(TypeColorHelper.findBackground(pokemonInformation?.types?.first()?.type?.id))

                )
            }
            Spacer(modifier = Modifier.width(36.dp))
            Text(
                text = stat.toString(),
                style = PokedexTextStyle.body
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    })
}