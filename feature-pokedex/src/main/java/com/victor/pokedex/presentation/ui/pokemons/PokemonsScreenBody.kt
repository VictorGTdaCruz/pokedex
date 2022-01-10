package com.victor.pokedex.presentation.ui.pokemons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.victor.pokedex.R
import com.victor.pokedex.domain.model.Pokemon
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonSprite
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.model.PokemonTypeWithSlot
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.Resource
import com.victor.pokedex.presentation.ui.components.EmptyUI
import com.victor.pokedex.presentation.ui.components.ErrorUI
import com.victor.pokedex.presentation.ui.components.LoadingUI
import com.victor.pokedex.presentation.ui.components.PokemonTypeCard
import com.victor.pokedex.presentation.ui.theme.Background
import com.victor.pokedex.presentation.ui.utils.TypeColorHelper

@Composable
internal fun PokemonsScreenBody(
    viewModel: PokedexViewModel,
    pokemonTypeId: Long,
    pokemonTypeName: String,
    onPokemonClick: (Pokemon) -> Unit
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.toolbarTitle = stringResource(
            id = R.string.pokemons_screen_title,
            formatArgs = arrayOf(pokemonTypeName.replaceFirstChar { it.titlecase() })
        )

        when (viewModel.pokemons.value) {
            Resource.Empty -> EmptyUI("We haven't detected any Pokémon of the $pokemonTypeName type yet!")
            Resource.Loading -> LoadingUI()
            is Resource.Error -> ErrorUI(message = viewModel.errorMessage) {
                viewModel.loadPokemonsFromType(pokemonTypeId)
            }
            is Resource.Success<*> -> PokemonList(
                pokemons = (viewModel.pokemons.value as Resource.Success<List<Pokemon>>).data,
                pokemonTypeId = pokemonTypeId,
                pokemonDetailsMap = viewModel.pokemonDetails,
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
private fun PokemonList(
    pokemons: List<Pokemon>,
    pokemonTypeId: Long,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    onPokemonClick: (Pokemon) -> Unit,
    loadDetails: @Composable (Long) -> Unit,
) {
    LazyColumn {
        items(pokemons) {
            PokemonCard(it, pokemonTypeId, pokemonDetailsMap, onPokemonClick, loadDetails)
        }
    }
}

@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    pokemonTypeId: Long,
    pokemonDetailsMap: Map<Long, PokemonDetails>,
    onPokemonClick: (Pokemon) -> Unit,
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
        Pokemon(name = "Pichu", id = 1, slot = 0),
        Pokemon(name = "Pikachu", id = 2, slot = 0),
        Pokemon(name = "Raichu", id = 3, slot = 0),
    )

    LazyColumn {
        items(pokemonList) {
            val details = PokemonDetails(
                id = 1L,
                name = "Nome",
                types = listOf(
                    PokemonTypeWithSlot(slot = 1, PokemonType(id = 13, name = "electric")),
                    PokemonTypeWithSlot(slot = 1, PokemonType(id = 9, name = "steel"))
                ),
                sprites = PokemonSprite("")
            )
            PokemonCard(
                pokemon = it,
                pokemonTypeId = 13,
                pokemonDetailsMap = mapOf(1L to details),
                onPokemonClick = { },
                loadDetails = { }
            )
        }
    }
}