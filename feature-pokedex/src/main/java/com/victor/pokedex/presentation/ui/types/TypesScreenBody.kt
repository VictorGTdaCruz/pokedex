package com.victor.pokedex.presentation.ui.types

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.features_common.ErrorHandler
import com.victor.features_common.Resource
import com.victor.features_common.getAsErrorResource
import com.victor.features_common.getAsSuccessResource
import com.victor.networking.PokedexException.UnexpectedException
import com.victor.pokedex.R
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.components.EmptyUI
import com.victor.pokedex.presentation.ui.components.ErrorUI
import com.victor.pokedex.presentation.ui.components.LoadingUI
import com.victor.pokedex.presentation.ui.components.PokemonTypeCard
import com.victor.pokedex.presentation.ui.theme.Background

@ExperimentalFoundationApi
@Composable
internal fun PokemonTypesScreenBody(
    viewModel: PokedexViewModel,
    onTypeClick: (PokemonType) -> Unit
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        with(viewModel) {
            toolbarTitle = stringResource(id = R.string.type_screen_title)

            when (pokemonTypes.value) {
                is Resource.Empty -> EmptyUI(message = stringResource(id = R.string.generic_empty_message))
                is Resource.Loading -> LoadingUI()
                is Resource.Error -> {
                    val exception = pokemonTypes.getAsErrorResource()?.exception ?: UnexpectedException
                    ErrorUI(
                        message = stringResource(
                            id = ErrorHandler.handleMessage(exception)
                        )
                    ) { loadPokemonTypes() }
                }
                is Resource.Success<*> -> {
                    val types = pokemonTypes.getAsSuccessResource<List<PokemonType>>()
                        ?.data
                        ?: emptyList()
                    TypeList(types, onTypeClick)
                }
            }

            LaunchedEffect(Unit) {
                loadPokemonTypes()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun TypeList(
    types: List<PokemonType>,
    onTypeClick: (PokemonType) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(types) {
                PokemonTypeCard(
                    type = it,
                    onTypeClick = onTypeClick,
                    iconSize = 30.dp,
                    iconPadding = 12.dp,
                    fontPadding = 12.dp,
                    fontSize = 20.sp
                )
            }
        }
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Preview() {
    TypeList(
        types = listOf(
            PokemonType(name = "normal", id = 1),
            PokemonType(name = "fighting", id = 2),
            PokemonType(name = "fire", id = 10),
            PokemonType(name = "grass", id = 12),
            PokemonType(name = "water", id = 11),
            PokemonType(name = "dark", id = 17)
        ).toMutableStateList(),
        onTypeClick = { }
    )
}