package com.victor.feature_pokedex.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.BottomCenter
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
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.feature_pokedex.presentation.ui.utils.formatPokemonName
import com.victor.features_common.ObserveResource

@Composable
internal fun HomeScreenBody(viewModel: PokedexViewModel) {
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

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    loadDetails: @Composable (Long) -> Unit,
) {
    val details = pokemonDetailsMap[pokemon.id]
    val typeId = details?.types?.firstOrNull()?.type?.id
    if (details == null)
        loadDetails(pokemon.id)

    Box (
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = if (typeId == null)
                        Color.White
                    else
                        TypeColorHelper.findBackground(typeId),
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
                    pokemon = pokemon,
                    pokemonDetails = details,
                    modifier = Modifier
                        .padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                )
            }
        }
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
                    .size(160.dp)
                    .padding(bottom = 8.dp, end = 8.dp)
                    .align(CenterEnd)
            )
    }
}

@Composable
private fun PokemonDetailsColumn(
    pokemon: Pokemon,
    pokemonDetails: PokemonDetails?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = pokemon.id.formatPokedexNumber(),
            color = Color.DarkGray,
            fontWeight = FontWeight.W700,
            fontSize = 12.sp,
        )
        Text(
            text = pokemon.name.formatPokemonName(),
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = 26.sp,
        )
        Row {
            pokemonDetails?.types?.forEach {
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