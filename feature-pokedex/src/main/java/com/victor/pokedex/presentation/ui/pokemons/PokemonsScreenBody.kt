package com.victor.pokedex.presentation.ui.pokemons

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.victor.features_common.ErrorHandler
import com.victor.features_common.Resource
import com.victor.features_common.getAsErrorResource
import com.victor.features_common.getAsSuccessResource
import com.victor.networking.PokedexException.UnexpectedException
import com.victor.pokedex.R
import com.victor.pokedex.domain.model.Pokemon
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonSprite
import com.victor.pokedex.domain.model.PokemonStat
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.model.PokemonTypeWithSlot
import com.victor.pokedex.domain.model.TypeDetails
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.components.EmptyUI
import com.victor.pokedex.presentation.ui.components.ErrorUI
import com.victor.pokedex.presentation.ui.components.LoadingUI
import com.victor.pokedex.presentation.ui.components.PokemonTypeCard
import com.victor.pokedex.presentation.ui.components.WhiteText
import com.victor.pokedex.presentation.ui.theme.Background
import com.victor.pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.pokedex.presentation.ui.utils.formatPokedexNumber
import com.victor.pokedex.presentation.ui.utils.formatPokemonHeight
import com.victor.pokedex.presentation.ui.utils.formatPokemonName
import com.victor.pokedex.presentation.ui.utils.formatPokemonWeight

@ExperimentalMaterialApi
@Composable
internal fun PokemonsScreenBody(
    viewModel: PokedexViewModel,
    pokemonTypeId: Long,
    pokemonTypeName: String
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        with(viewModel) {
            toolbarTitle = stringResource(
                id = R.string.pokemons_screen_title,
                formatArgs = arrayOf(pokemonTypeName.replaceFirstChar { it.titlecase() })
            )

            when (typeDetails.value) {
                is Resource.Empty -> PokemonsEmpty(pokemonTypeName)
                is Resource.Loading -> LoadingUI()
                is Resource.Error -> {
                    val exception = typeDetails.getAsErrorResource()?.exception ?: UnexpectedException
                    ErrorUI(
                        message = stringResource(
                            id = ErrorHandler.handleMessage(exception)
                        )
                    ) { loadPokemonsFromType(pokemonTypeId) }
                }
                is Resource.Success<*> -> {
                    val pokemonList = typeDetails.getAsSuccessResource<TypeDetails>()
                        ?.data?.pokemons
                        ?: emptyList()
                    if (pokemonList.isEmpty())
                        PokemonsEmpty(pokemonTypeName)
                    else
                        PokemonList(
                            pokemons = pokemonList,
                            pokemonTypeId = pokemonTypeId,
                            pokemonDetailsMap = pokemonDetails,
                            loadDetails = {
                                LaunchedEffect(Unit) {
                                    loadPokemonDetails(it)
                                }
                            }
                        )
                }
            }

            LaunchedEffect(Unit) {
                loadPokemonsFromType(pokemonTypeId)
            }
        }
    }
}

@Composable
private fun PokemonsEmpty(pokemonTypeName: String) {
    EmptyUI(stringResource(id = R.string.type_empty_message, pokemonTypeName))
}

@Composable
private fun PokemonList(
    pokemons: List<Pokemon>,
    pokemonTypeId: Long,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    loadDetails: @Composable (Long) -> Unit,
) {
    LazyColumn {
        items(pokemons) {
            PokemonCard(it, pokemonTypeId, pokemonDetailsMap, loadDetails)
        }
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    pokemonTypeId: Long,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    loadDetails: @Composable (Long) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    val details = pokemonDetailsMap[pokemon.id]
    if (details == null)
        loadDetails(pokemon.id)

    Card(
        backgroundColor = TypeColorHelper.findBackground(pokemonTypeId),
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                onClick = { isExpanded = !isExpanded }
            )
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                ),
            )
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                PokemonNumberAndNameColumn(
                    pokemonId = pokemon.id,
                    pokemonName = pokemon.name,
                    modifier = Modifier
                        .padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                        .align(CenterVertically)
                        .weight(1f)
                )

                PokemonTypesColumn(
                    types = details?.types ?: emptyList(),
                    modifier = Modifier
                        .align(CenterVertically)
                        .weight(0.75f)
                )

                PokemonImage(
                    details = details,
                    modifier = Modifier
                        .align(CenterVertically)
                        .weight(1f)
                )
            }

            if (isExpanded && details != null)
                PokemonCardExpanded(details = details)
        }
    }
}

@Composable
private fun PokemonNumberAndNameColumn(
    pokemonId: Long,
    pokemonName: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = pokemonId.formatPokedexNumber(),
            color = Color.White,
            modifier = Modifier
        )
        Text(
            text = pokemonName.formatPokemonName(),
            fontSize = 18.sp,
            color = Color.White,
        )
    }
}

@Composable
private fun PokemonTypesColumn(
    types: List<PokemonTypeWithSlot>,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
    ) {
        types.forEach {
            PokemonTypeCard(
                type = PokemonType(
                    id = it.type.id,
                    name = it.type.name
                ),
                onTypeClick = {},
                iconSize = 14.dp,
                fontSize = 10.sp,
                cardPadding = 2.dp
            )
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

@Composable
private fun PokemonCardExpanded(
    details: PokemonDetails
) {
    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        HeightAndWeightColumn(
            height = details.height,
            weight = details.weight,
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
        )
        BaseStatsColumn(
            stats = details.stats,
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
        )
    }
}

@Composable
private fun HeightAndWeightColumn(
    height: Int,
    weight: Int,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
    ) {
        WhiteText(
            text = stringResource(id = R.string.height),
            fontSize = 18.sp,
            modifier = Modifier.align(CenterHorizontally)
        )
        WhiteText(
            text = height.formatPokemonHeight(),
            modifier = Modifier.align(CenterHorizontally)
        )
        Spacer(
            modifier = Modifier.size(24.dp)
        )
        WhiteText(
            text = stringResource(id = R.string.weight),
            fontSize = 18.sp,
            modifier = Modifier.align(CenterHorizontally)
        )
        WhiteText(
            text = weight.formatPokemonWeight(),
            modifier = Modifier.align(CenterHorizontally)
        )
    }
}

@Composable
private fun BaseStatsColumn(
    stats: List<PokemonStat>,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        WhiteText(
            text = stringResource(id = R.string.pokemon_base_stats),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)
        )
        stats.forEach {
            WhiteText(
                text = stringResource(
                    id = R.string.stat, it.name, it.baseStat
                ),
                modifier = Modifier.align(CenterHorizontally)
            )
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
                pokemonTypeId = 13,
                pokemonDetailsMap = mapOf(1L to details),
                loadDetails = { }
            )
        }
    }
}