package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonInformation
import com.victor.feature_pokedex.presentation.ui.theme.Fairy
import com.victor.feature_pokedex.presentation.ui.theme.Ghost
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.beautifyString
import com.victor.feature_pokedex.presentation.ui.utils.formatEV
import com.victor.feature_pokedex.presentation.ui.utils.formatEggGroups
import com.victor.feature_pokedex.presentation.ui.utils.formatFlavorText
import com.victor.feature_pokedex.presentation.ui.utils.formatPercentage
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
fun aboutTab(pokemonInformation: PokemonInformation) {
    val typeColor = TypeColorHelper.findBackground(pokemonInformation.types.first().type.id)
    Column(
        Modifier.padding(24.dp)
    ) {
        Text(
            text = pokemonInformation.formatFlavorText(),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.about_tab_pokedex_data),
            color = typeColor,
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_species),
            value = pokemonInformation.genera
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_height),
            value = stringResource(
                id = R.string.about_tab_height_in_meters,
                pokemonInformation.height.toString()
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_weight),
            value = stringResource(
                id = R.string.about_tab_weight_in_kilograms,
                pokemonInformation.weight.toString()
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_abilities), description = {
            Column(
                modifier = Modifier.weight(weight = 0.7f)
            ) {
                pokemonInformation.abilities.forEachIndexed { index, item ->
                    Text(
                        text = if (item.isHidden)
                            stringResource(
                                id = R.string.about_tab_ability_item_hidden,
                                index + 1,
                                item.name.beautifyString()
                            )
                        else
                            stringResource(
                                id = R.string.about_tab_ability_item,
                                index + 1,
                                item.name.beautifyString()
                            ),
                        style = if (index == 0)
                            PokedexTextStyle.body
                        else
                            PokedexTextStyle.description
                    )
                }
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_weakness), value = "??")
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.about_tab_training),
            color = typeColor,
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_ev_yield),
            value = pokemonInformation.stats.formatEV()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_catch_rate), description = {
            Row(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = pokemonInformation.captureRate.toString(),
                    style = PokedexTextStyle.body,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(
                        id = R.string.about_tab_catch_rate_with_pokeball,
                        pokemonInformation.captureProbability.formatPercentage()
                    ),
                    style = PokedexTextStyle.description,
                    modifier = Modifier.align(CenterVertically)
                )
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_base_xp),
            value = pokemonInformation.baseXp.toString()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_growth_rate),
            value = pokemonInformation.growthRate.beautifyString()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.about_tab_breeding),
            color = typeColor,
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_gender), description = {
            Row(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.about_tab_gender_male,
                        pokemonInformation.maleRate.toString()
                    ),
                    color = Ghost,
                    style = PokedexTextStyle.body,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(
                        id = R.string.about_tab_gender_female,
                        pokemonInformation.femaleRate.toString()
                    ),
                    color = Fairy,
                    style = PokedexTextStyle.body,
                )
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(
            title = stringResource(id = R.string.about_tab_egg_groups),
            value = pokemonInformation.eggGroups.formatEggGroups()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_egg_cycles), description = {
            Row(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = pokemonInformation.hatchCounter.toString(),
                    style = PokedexTextStyle.body,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(
                        id = R.string.about_tab_egg_cycles_in_steps,
                        pokemonInformation.hatchSteps
                    ),
                    style = PokedexTextStyle.description,
                    modifier = Modifier.align(CenterVertically)
                )
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
    }
}