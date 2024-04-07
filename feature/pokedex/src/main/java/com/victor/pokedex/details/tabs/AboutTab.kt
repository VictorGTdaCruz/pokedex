package com.victor.pokedex.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.pokedex.R
import com.victor.pokedex.components.PokemonTypeIcon
import com.victor.pokedex.theme.Fairy
import com.victor.pokedex.theme.Ghost
import com.victor.pokedex.utils.TypeColorHelper
import com.victor.pokedex.utils.beautifyFloatToString
import com.victor.pokedex.utils.beautifyString
import com.victor.pokedex.utils.formatEV
import com.victor.pokedex.utils.formatEggGroups
import com.victor.pokedex.utils.formatFlavorText
import com.victor.pokedex.utils.formatFloatToString
import com.victor.pokedex.utils.formatIntToString
import com.victor.pokedex.utils.formatKgToLb
import com.victor.pokedex.utils.formatMToFeetAndInches
import com.victor.pokedex.utils.formatPercentage
import com.victor.pokedex.components.PokedexTextStyle
import com.victor.pokedex.components.PokedexTextStyle.bold
import com.victor.model.PokemonInformation

@Composable
fun aboutTab(pokemonInformation: PokemonInformation) {
    val typeColor = TypeColorHelper.findBackground(pokemonInformation.typeList.first().id)
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
        val heightInFeetAndInches = pokemonInformation.height.formatMToFeetAndInches()
        TabCellWithAuxText(
            title = stringResource(id = R.string.about_tab_height),
            value = stringResource(
                id = R.string.about_tab_height_in_meters,
                pokemonInformation.height.beautifyFloatToString()
            ),
            description = stringResource(
                id = R.string.about_tab_height_in_feet_and_inches,
                heightInFeetAndInches.first.toString(),
                heightInFeetAndInches.second.toString()
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCellWithAuxText(
            title = stringResource(id = R.string.about_tab_weight),
            value = stringResource(
                id = R.string.about_tab_weight_in_kilograms,
                pokemonInformation.weight.beautifyFloatToString()
            ),
            description = stringResource(
                id = R.string.about_tab_weight_in_lb,
                pokemonInformation.weight.formatKgToLb().formatFloatToString()
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_abilities), description = {
            Column {
                pokemonInformation.abilityList.forEachIndexed { index, item ->
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
        TabCell(
            title = stringResource(id = R.string.about_tab_weakness),
            description = {
                LazyRow {
                    items(pokemonInformation.weaknessList.size) {
                        PokemonTypeIcon(
                            type = pokemonInformation.weaknessList[it],
                            iconSize = 12.dp,
                            cardPadding = 0.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        )
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
        TabCellWithAuxText(
            title = stringResource(id = R.string.about_tab_catch_rate),
            value = pokemonInformation.captureRate.toString(),
            description = stringResource(
                id = R.string.about_tab_catch_rate_with_pokeball,
                pokemonInformation.captureProbability.formatPercentage()
            )
        )
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
            if (pokemonInformation.femaleRate == null) {
                Text(
                    text = stringResource(id = R.string.about_tab_genderless),
                    style = PokedexTextStyle.body,
                )
            } else {
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
            value = pokemonInformation.eggGroupList.formatEggGroups()
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCellWithAuxText(
            title = stringResource(id = R.string.about_tab_egg_cycles),
            value = pokemonInformation.hatchCounter.toString(),
            description = stringResource(
                id = R.string.about_tab_egg_cycles_in_steps,
                pokemonInformation.hatchSteps.formatIntToString()
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}