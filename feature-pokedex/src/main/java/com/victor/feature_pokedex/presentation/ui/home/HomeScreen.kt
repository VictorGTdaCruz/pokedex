package com.victor.feature_pokedex.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonSprite
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.PokemonTypeWithSlot
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.components.PokemonTypeBadge
import com.victor.feature_pokedex.presentation.ui.theme.PokedexBlue
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonName
import com.victor.features_common.ObserveState
import com.victor.features_common.State
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@Composable
internal fun HomeScreenBody(viewModel: PokedexViewModel) {
    with(viewModel) {
        LaunchedEffect(Unit) {
            loadPokemonList()
        }

        Column {
            PokemonSearchTextField(isEnabled = currentPokemonList.value is State.Success<*>) {
                searchPokemon(it)
            }
            ObserveState<List<Pokemon>>(state = currentPokemonList) { pokemonList ->
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(pokemonList.count()) {
                        val pokemon = pokemonList[it]
                        val pokemonDetails = pokemonDetails[pokemon.id]

                        if (pokemonDetails == null) {
                            PokemonCardLoading()
                            LaunchedEffect(pokemon.id) {
                                loadPokemonDetails(pokemon.id)
                            }
                        } else {
                            PokemonCard(pokemonDetails)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonSearchTextField(isEnabled: Boolean, onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    TextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearch.invoke(it)
        },
        enabled = isEnabled,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                color = Color.Gray,
                style = PokedexTextStyle.body
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Composable
private fun PokemonCardLoading() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 26.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .height(136.dp)
    ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = PokedexBlue,
                    strokeWidth = 2.dp,
                    modifier = Modifier.align(Center)
                )
            }
    }
}

@Composable
private fun PokemonCard(pokemonDetails: PokemonDetails) {
    Box (
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = TypeColorHelper.findBackground(pokemonDetails.types.firstOrNull()?.type?.id),
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier
                .padding(top = 24.dp)
                .align(BottomCenter)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokemon_card_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                PokemonDetailsColumn(
                    pokemonDetails = pokemonDetails,
                    modifier = Modifier.padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                )
            }
        }
        Image(
            painter = rememberImagePainter(
                data = pokemonDetails.sprites.otherFrontDefault,
                builder = {
                    crossfade(true)
                    crossfade(500)
                }
            ),
            contentDescription = stringResource(id = R.string.content_description_pokemon_image),
            modifier = Modifier
                .size(160.dp)
                .padding(bottom = 8.dp, end = 8.dp)
                .align(CenterEnd)
        )
    }
}

@Composable
private fun PokemonDetailsColumn(
    pokemonDetails: PokemonDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = pokemonDetails.id.formatPokedexNumber(),
            style = PokedexTextStyle.description.bold(),
            color = Color.DarkGray,
        )
        Text(
            text = pokemonDetails.name.formatPokemonName(),
            style = PokedexTextStyle.subtitle.bold(),
            color = Color.White,
        )
        Row {
            pokemonDetails.types.forEach {
                Box(
                    modifier = Modifier.padding(end = 6.dp, top = 4.dp)
                ) {
                    PokemonTypeBadge(
                        type = PokemonType(
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

@Preview
@Composable
private fun Preview() {
    LazyColumn {
        items(3) {
            val pokemonDetails = PokemonDetails(
                id = 1L,
                name = "Name",
                height = 20,
                weight = 70,
                types = listOf(
                    PokemonTypeWithSlot(slot = 1, PokemonType(id = 13, name = "electric")),
                    PokemonTypeWithSlot(slot = 1, PokemonType(id = 9, name = "steel"))
                ),
                sprites = PokemonSprite("", ""),
                stats = listOf()
            )
            PokemonCard(pokemonDetails = pokemonDetails)
        }
    }
}