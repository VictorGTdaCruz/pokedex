package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonSpecies
import com.victor.feature_pokedex.presentation.ui.theme.Fairy
import com.victor.feature_pokedex.presentation.ui.theme.Ghost
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.formatCatchProbability
import com.victor.feature_pokedex.presentation.ui.utils.formatFlavorText
import com.victor.feature_pokedex.presentation.ui.utils.formatHatchCounter
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonHeight
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonWeight
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold
import java.util.Locale

@Composable
fun aboutTab(pokemonDetails: PokemonDetails?, pokemonSpecies: PokemonSpecies) {
    Column(
        Modifier.padding(24.dp)
    ) {
        Text(
            text = pokemonSpecies.flavorText.formatFlavorText(pokemonDetails?.name) ?: "",
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.about_tab_pokedex_data),
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_species), value = pokemonSpecies.genera)
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_height), value = pokemonDetails?.height?.formatPokemonHeight() ?: "")
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_weight), value = pokemonDetails?.weight?.formatPokemonWeight() ?: "")
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_abilities), description = {
            Column(
                modifier = Modifier.weight(weight = 0.7f)
            ) {
                pokemonDetails?.abilities?.forEachIndexed { index, item ->
                    Text( // TODO format correctly here
                        text = "${index + 1}. ${item.name.replace("-", " ").replaceFirstChar { it.titlecase(Locale.getDefault()) }} ${if(item.isHidden) " (hidden ability)" else ""}",
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
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_ev_yield), value = "??")
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_catch_rate), description = {
            Row(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = pokemonSpecies.captureRate.toString(),
                    style = PokedexTextStyle.body,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = pokemonSpecies.captureProbability.formatCatchProbability(),
                    style = PokedexTextStyle.description,
                    modifier = Modifier.align(CenterVertically)
                )
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_base_friendship), value = "??")
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_base_xp), value = pokemonDetails?.baseXp.toString())
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_growth_rate), value = pokemonSpecies.growthRate
            .replace("-", " ")
            .replaceFirstChar { it.titlecase(Locale.getDefault()) })
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.about_tab_breeding),
            color = TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id),
            style = PokedexTextStyle.body.bold(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_gender), description = {
            Row(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = stringResource(id = R.string.about_tab_gender_male, (100 - pokemonSpecies.genderRate * 12.5).toString()),
                    color = Ghost,
                    style = PokedexTextStyle.body,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.about_tab_gender_female, (pokemonSpecies.genderRate * 12.5).toString()),
                    color = Fairy,
                    style = PokedexTextStyle.body,
                )
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_egg_groups), value = pokemonSpecies.eggGroups.joinToString(", ") { eggGroup ->
            eggGroup.replaceFirstChar { it.titlecase(Locale.getDefault()) }
        })
        Spacer(modifier = Modifier.height(12.dp))
        TabCell(title = stringResource(id = R.string.about_tab_egg_cycles), description = {
            Row(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(
                    text = pokemonSpecies.hatchCounter.toString(),
                    style = PokedexTextStyle.body,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = pokemonSpecies.hatchCounter.formatHatchCounter(),
                    style = PokedexTextStyle.description,
                    modifier = Modifier.align(CenterVertically)
                )
            }
        })
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun TabCell(title: String, value: String? = null, description: @Composable (() -> Unit)? = null) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = PokedexTextStyle.description,
            modifier = Modifier
                .align(CenterVertically)
                .weight(weight = 0.3f)
        )
        if (value != null)
            Text(
                text = value,
                style = PokedexTextStyle.body,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(weight = 0.7f)
            )
        else
            description?.invoke()
    }
}