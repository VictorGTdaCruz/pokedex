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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonSprite
import com.victor.feature_pokedex.domain.model.PokemonStat
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.PokemonTypeWithSlot
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.components.PokemonTypeBadge
import com.victor.feature_pokedex.presentation.ui.theme.PokedexBlue
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonName

@Composable
internal fun HomeScreenBody(viewModel: PokedexViewModel) {
    with(viewModel) {
        val pokemonList = pokemonList.collectAsLazyPagingItems()

        LazyColumn {
            items(pokemonList.itemCount) {
                val pokemon = pokemonList[it]
                val pokemonDetails = pokemonDetails[pokemon?.id]

                if (pokemonDetails == null) {
                    PokemonCardLoading()
                    LaunchedEffect(Unit) {
                        loadPokemonDetails(pokemon?.id ?: 0)
                    }
                } else {
                    PokemonCard(pokemonDetails)
                }
            }

            when (pokemonList.loadState.source.append) {
                is LoadState.Loading -> item { PokemonPagingListLoading() }
                is LoadState.Error -> item { PokemonPagingListError() }
                else -> { }
            }
        }

        LaunchedEffect(Unit) {
            loadPokemonList()
        }
    }
}

@Composable
private fun PokemonPagingListLoading() {
    Box (
        modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
    ) {
        CircularProgressIndicator(
            color = PokedexBlue,
            modifier = Modifier.align(Center)
        )
    }
}

@Composable
private fun PokemonPagingListError() {
    Box (
        modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
    ) {
        Row(modifier = Modifier.align(Center)) {
            Text("Looks like there was a problem!")
            Button(onClick = { /*TODO*/ }) {
                Text("Retry!")
            }
        }
    }
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
            color = Color.DarkGray,
            fontWeight = FontWeight.W700,
            fontSize = 12.sp,
        )
        Text(
            text = pokemonDetails.name.formatPokemonName(),
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = 26.sp,
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
                        fontSize = 12.sp,
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
                stats = listOf(
                    PokemonStat(name = "test", baseStat = 1),
                    PokemonStat(name = "test", baseStat = 2),
                    PokemonStat(name = "test", baseStat = 3),
                    PokemonStat(name = "test", baseStat = 4),
                    PokemonStat(name = "test", baseStat = 5),
                    PokemonStat(name = "test", baseStat = 6)
                )
            )
            PokemonCard(pokemonDetails = pokemonDetails)
        }
    }
}