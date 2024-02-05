package com.victor.feature_pokedex.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonSprite
import com.victor.feature_pokedex.domain.model.PokemonStat
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.PokemonTypeWithSlot
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.components.PokemonTypeBadge
import com.victor.feature_pokedex.presentation.ui.theme.Background
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonName
import com.victor.features_common.ObserveResource

@ExperimentalMaterialApi
@Composable
internal fun HomeScreenBody(viewModel: PokedexViewModel) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        with(viewModel) {
            ObserveResource<List<Pokemon>>(
                state = pokemonList,
                onRetry = { loadPokemonList() },
                onSuccess = {
                    LazyColumn {
                        items(it) {
                            PokemonCard(it, pokemonDetails) {
                                LaunchedEffect(Unit) { // TODO n sei se precisa desse launched aqui
                                    loadPokemonDetails(it)
                                }
                            }
                        }
                    }
                }
            )

            LaunchedEffect(Unit) {
                loadPokemonList()
            }
        }
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    loadDetails: @Composable (Long) -> Unit,
) {
    val details = pokemonDetailsMap[pokemon.id]
    if (details == null)
        loadDetails(pokemon.id)

    Card(
        backgroundColor = if (details == null) Color.Transparent else TypeColorHelper.findBackground(details.id),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            PokemonDetailsColumn(
                pokemon = pokemon,
                pokemonDetails = details,
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                    .weight(1f)
            )
            PokemonImage(
                details = details,
                modifier = Modifier
                    .align(CenterVertically)
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun PokemonDetailsColumn(
    pokemon: Pokemon,
    pokemonDetails: PokemonDetails?,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = pokemon.id.formatPokedexNumber(),
            color = Color.White,
            modifier = Modifier
        )
        Text(
            text = pokemon.name.formatPokemonName(),
            fontSize = 18.sp,
            color = Color.White,
        )
        Row(
            modifier = modifier
        ) {
            pokemonDetails?.types?.forEach {
                PokemonTypeBadge(
                    type = PokemonType(
                        id = it.type.id,
                        name = it.type.name
                    ),
                    onClick = {},
                    iconSize = 14.dp,
                    fontSize = 10.sp,
                    cardPadding = 2.dp
                )
            }
        }
    }
}

@Composable
private fun PokemonImage(
    details: PokemonDetails?,
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {
        if (details != null)
            Image(
                painter = rememberImagePainter(
                    data = details.sprites.otherFrontDefault,
                    builder = {
                        crossfade(true)
                        crossfade(500)
                    }
                ),
                contentDescription = stringResource(id = R.string.content_description_pokemon_image),
                modifier = Modifier
                    .size(90.dp)
                    .align(CenterHorizontally)
            )
    }
}

@Preview
@Composable
private fun Preview() {
    val pokemonList = listOf(
        Pokemon(name = "Pichu", id = 1, slot = 0),
        Pokemon(name = "Pikachu", id = 2, slot = 0),
        Pokemon(name = "Raichu", id = 3, slot = 0),
    )

    LazyColumn {
        items(pokemonList) {
            val details = PokemonDetails(
                id = 1L,
                name = "Name",
                height = 20,
                weight = 70,
                types = listOf(
                    PokemonTypeWithSlot(slot = 1, PokemonType(id = 13, name = "electric")),
                    PokemonTypeWithSlot(slot = 1, PokemonType(id = 9, name = "steel"))
                ),
                sprites = PokemonSprite("", ""),
                stats = listOf(
                    PokemonStat(name = "test", baseStat = 1),
                    PokemonStat(name = "test", baseStat = 2),
                    PokemonStat(name = "test", baseStat = 3),
                    PokemonStat(name = "test", baseStat = 4),
                    PokemonStat(name = "test", baseStat = 5),
                    PokemonStat(name = "test", baseStat = 6)
                )
            )
            PokemonCard(
                pokemon = it,
                pokemonDetailsMap = mapOf(1L to details),
                loadDetails = { }
            )
        }
    }
}