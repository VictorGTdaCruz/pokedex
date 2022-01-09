package com.victor.pokedex.presentation.ui.pokemons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.victor.pokedex.R
import com.victor.pokedex.data.mapper.mapIdFromUrl
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonSimplified
import com.victor.pokedex.domain.model.PokemonSprite
import com.victor.pokedex.domain.model.PokemonTypeSimplified
import com.victor.pokedex.domain.model.Tste
import com.victor.pokedex.domain.model.Tstet
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.components.Loading
import com.victor.pokedex.presentation.ui.components.PokemonTypeCard
import com.victor.pokedex.presentation.ui.theme.Background
import com.victor.pokedex.presentation.ui.utils.TypeColorHelper

@Composable
internal fun PokemonsScreenBody(
    viewModel: PokedexViewModel,
    pokemonTypeId: Long,
    pokemonTypeName: String,
    onPokemonClick: (PokemonSimplified) -> Unit
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.toolbarTitle = stringResource(
            id = R.string.pokemons_screen_title,
            formatArgs = arrayOf(pokemonTypeName.replaceFirstChar { it.titlecase() })
        )
        when {
            viewModel.isLoading -> Loading()
            viewModel.pokemons.isEmpty() -> Empty(pokemonTypeName)
            else -> Content(
                viewModel = viewModel,
                pokemonTypeId = pokemonTypeId,
                pokemonDetailsMap = viewModel.details,
                onPokemonClick = onPokemonClick,
                loadDetails = {
                    LaunchedEffect(Unit) {
                        viewModel.loadPokemonDetails(it)
                    }
                }
            )
        }

        LaunchedEffect(Unit) {
            viewModel.loadPokemonsFromType(pokemonTypeId)
        }
    }
}

@Composable
private fun Content(
    viewModel: PokedexViewModel,
    pokemonTypeId: Long,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    onPokemonClick: (PokemonSimplified) -> Unit,
    loadDetails: @Composable (Long) -> Unit,
) {
    LazyColumn {
        items(viewModel.pokemons) {
            PokemonCard(it, pokemonTypeId, pokemonDetailsMap, onPokemonClick, loadDetails)
        }
    }
}

@Composable
private fun Empty(pokemonTypeName: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_pikachu_resting),
            contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        )
        Text(
            text = "We haven't detected any Pokémon of the $pokemonTypeName type yet!",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun PokemonCard(
    pokemon: PokemonSimplified,
    pokemonTypeId: Long,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    onPokemonClick: (PokemonSimplified) -> Unit,
    loadDetails: @Composable (Long) -> Unit,
) {
    Card(
        backgroundColor = TypeColorHelper.find(pokemonTypeId),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onPokemonClick(pokemon) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            val details = pokemonDetailsMap[pokemon.id]
            if (details == null)
                loadDetails(pokemon.id)

            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Text(
                    text = "#${pokemon.id}",
                    color = Color.White,
                    modifier = Modifier
                )
                Text(
                    text = pokemon.name.replaceFirstChar { it.titlecase() },
                    fontSize = 18.sp,
                    color = Color.White,
                )
            }

            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.75f)
            ) {
                details?.types?.forEach {
                    PokemonTypeCard(
                        type = PokemonTypeSimplified(
                            id = it.type.url.mapIdFromUrl(),
                            name = it.type.name
                        ),
                        onTypeClick = {},
                        iconSize = 14.dp,
                        fontSize = 10.sp,
                        cardPadding = 2.dp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                if (details != null)
                    Image(
                        painter = rememberImagePainter(
                            data = details.sprites.frontDefault,
                            builder = {
                                crossfade(true)
                                crossfade(500)
                            }
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(90.dp)
                            .align(CenterHorizontally)
                    )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val pokemonList = listOf(
        PokemonSimplified(name = "Pichu", id = 1, slot = 0),
        PokemonSimplified(name = "Pikachu", id = 2, slot = 0),
        PokemonSimplified(name = "Raichu", id = 3, slot = 0),
    )

    LazyColumn {
        items(pokemonList) {
            val details = PokemonDetails(
                id = 1L,
                name = "Nome",
                types = listOf(
                    Tste(slot = 1, Tstet(name = "electric", "")),
                    Tste(slot = 1, Tstet(name = "steel", ""))
                ),
                sprites = PokemonSprite("")
            )
            PokemonCard(
                pokemon = it,
                pokemonTypeId = 13,
                pokemonDetailsMap = mapOf(1L to details),
                onPokemonClick = { },
                loadDetails = { })
        }
    }
}