package com.victor.pokedex.details.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.victor.pokedex.R
import com.victor.pokedex.utils.TypeColorHelper
import com.victor.pokedex.utils.beautifyString
import com.victor.pokedex.utils.formatPokedexNumber
import com.victor.pokedex.components.PokedexTextStyle
import com.victor.pokedex.theme.LightGray
import com.victor.model.Pokemon
import com.victor.model.PokemonEvolution
import com.victor.model.PokemonInformation

@Composable
fun evolutionTab(pokemonInformation: PokemonInformation, onPokemonClick: (Int) -> Unit) {
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.evolution_tab_title),
            color = TypeColorHelper.findBackground(pokemonInformation.typeList.first().id),
            style = PokedexTextStyle.body,
        )
        Spacer(modifier = Modifier.height(24.dp))
        pokemonInformation.evolutionList.forEach {
            EvolutionCell(it, onPokemonClick)
            Spacer(modifier = Modifier.height(24.dp))
        }
        if (pokemonInformation.evolutionList.isEmpty())
            EmptyEvolutionCell(pokemonInformation)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun EvolutionCell(evolution: PokemonEvolution, onPokemonClick: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        EvolutionPokemonList(pokemonList = evolution.from, onPokemonClick)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(LightGray),
                modifier = Modifier.rotate(180f)
            )
            Text(
                text = stringResource(id = R.string.evolution_tab_level, evolution.minLevel.toString()),
                color = Color.Black,
                style = PokedexTextStyle.description,
            )
        }
        EvolutionPokemonList(pokemonList = evolution.to, onPokemonClick)
    }
}

@Composable
private fun RowScope.EvolutionPokemonList(pokemonList: List<Pokemon>?, onPokemonClick: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(1f)
    ) {
        pokemonList?.forEach {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(90.dp)
                    .clip(CircleShape)
                    .clickable {
                        onPokemonClick(it.id)
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.pokeball_background_full),
                    contentDescription = it.name,
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = rememberImagePainter(data = it.sprite),
                    contentDescription = it.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            Text(
                text = it.id.formatPokedexNumber(),
                style = PokedexTextStyle.description,
                color = Color.DarkGray,
            )
            Text(
                text = it.name.beautifyString(),
                style = PokedexTextStyle.body,
                color = Color.Black,
            )
        }
    }
}

@Composable
private fun EmptyEvolutionCell(pokemonInformation: PokemonInformation) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.size(90.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.pokeball_background_full),
                contentDescription = pokemonInformation.name,
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painter = rememberImagePainter(data = pokemonInformation.sprite),
                contentDescription = pokemonInformation.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
        Text(
            text = pokemonInformation.id.formatPokedexNumber(),
            style = PokedexTextStyle.description,
            color = Color.DarkGray,
        )
        Text(
            text = pokemonInformation.name.beautifyString(),
            style = PokedexTextStyle.body,
            color = Color.Black,
        )
    }
}