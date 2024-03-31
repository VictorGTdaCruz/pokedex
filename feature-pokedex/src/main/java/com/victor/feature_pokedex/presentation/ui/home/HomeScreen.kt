package com.victor.feature_pokedex.presentation.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonSimple
import com.victor.feature_pokedex.domain.model.TypeSimple
import com.victor.feature_pokedex.presentation.ui.components.PokemonColumn
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.FilterBottomSheet
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.GenerationBottomSheet
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.SortBottomSheet
import com.victor.feature_pokedex.presentation.ui.theme.PokedexBlue
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.observeStateInsideLazyList
import com.victor.features_common.theme.LightGray
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreenBody(viewModel: HomeViewModel, onPokemonClick: (Int) -> Unit) {
    val scrollState = rememberLazyListState()
    val isFilterBottomSheetVisible = mutableStateOf(false)
    val isSortBottomSheetVisible = mutableStateOf(false)
    val isGenerationBottomSheetVisible = mutableStateOf(false)

    LaunchedEffect(Unit) {
        viewModel.getPokemonList()
        viewModel.getTypeList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            state = scrollState
        ) {
            item {
                Column {
                    HomeAppBar(
                        onFilterClick = { isFilterBottomSheetVisible.value = true },
                        onSortClick = { isSortBottomSheetVisible.value = true },
                        onGenerationClick = { isGenerationBottomSheetVisible.value = true }
                    )
                    PokemonSearchTextField(
                        value = viewModel.searchText.value,
                        onValueChange = viewModel::searchPokemon,
                        isEnabled = viewModel.isSearchEnabled(),
                    )
                }
            }
            observeStateInsideLazyList<List<PokemonSimple>>(
                state = viewModel.currentPokemonList,
                onRetry = { viewModel.getPokemonList() }
            ) { pokemonList ->
                items(pokemonList.count()) {
                    val pokemonSimple = pokemonList[it]
                    val pokemon = viewModel.pokemon[pokemonSimple.id]
                    if (pokemon == null) {
                        PokemonCardLoading()
                        LaunchedEffect(pokemonSimple.id) {
                            viewModel.getPokemon(pokemonSimple.id)
                        }
                    } else {
                        PokemonCard(pokemon, onPokemonClick)
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        ScrollToTopFAB(scrollState)
    }

    BottomSheets(
        viewModel = viewModel,
        isFilterBottomSheetVisible = isFilterBottomSheetVisible,
        isSortBottomSheetVisible = isSortBottomSheetVisible,
        isGenerationBottomSheetVisible = isGenerationBottomSheetVisible
    )
}

@Composable
private fun BottomSheets(
    viewModel: HomeViewModel,
    isFilterBottomSheetVisible: MutableState<Boolean>,
    isSortBottomSheetVisible: MutableState<Boolean>,
    isGenerationBottomSheetVisible: MutableState<Boolean>,
) {
    with(viewModel) {
        if (isFilterBottomSheetVisible.value)
            FilterBottomSheet(
                typeListState = pokemonTypes,
                selectedIdRangeState = selectedIdRange.value ?: 0f..0f,
                fullIdRangeState = fullIdRange.value ?: 0f..0f,
                onDismiss = { isFilterBottomSheetVisible.value = false },
                isPokemonTypeFilterIconFilled = ::isPokemonTypeFilterIconFilled,
                onPokemonTypeFilterIconClick = ::onPokemonTypeFilterIconClick,
                onPokemonTypeFilterResetClick = ::onPokemonTypeFilterResetClick,
                onPokemonTypeFilterApplyClick = {
                    isFilterBottomSheetVisible.value = false
                    getPokemonList()
                },
                onRangeFilterUpdate = ::onRangeFilterUpdate,

                )
        if (isSortBottomSheetVisible.value)
            SortBottomSheet(
                onDismiss = { isSortBottomSheetVisible.value = false },
                onPokemonSortClick = ::onPokemonSortClick,
                isSortButtonEnabled = ::isSortButtonEnabled
            )
        if (isGenerationBottomSheetVisible.value)
            GenerationBottomSheet(
                onDismiss = { isGenerationBottomSheetVisible.value = false },
                onPokemonGenerationClick = ::onPokemonGenerationClick,
                isGenerationButtonEnabled = ::isGenerationButtonEnabled
            )
    }
}

@Composable
private fun PokemonSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = PokedexTextStyle.body,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = LightGray,
            focusedContainerColor = LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 8.dp)
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
private fun PokemonCard(pokemon: Pokemon, onPokemonClick: (Int) -> Unit) {
    Box(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 2.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = TypeColorHelper.findBackground(pokemon.typeList.firstOrNull()?.id),
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier
                .padding(top = 24.dp)
                .align(BottomCenter)
                .fillMaxWidth()
                .clickable { onPokemonClick.invoke(pokemon.id) }
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
                PokemonColumn(
                    id = pokemon.id,
                    name = pokemon.name,
                    typeList = pokemon.typeList,
                    modifier = Modifier.padding(start = 24.dp, end = 8.dp, top = 24.dp, bottom = 24.dp)
                )
            }
        }
        Image(
            painter = rememberImagePainter(
                data = pokemon.sprite,
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
fun BoxScope.ScrollToTopFAB(scrollState: LazyListState) {
    AnimatedVisibility(
        visible = derivedStateOf { scrollState.firstVisibleItemIndex }.value > 1,
        enter = slideInVertically(
            initialOffsetY = { 300 }
        ),
        exit = slideOutVertically(
            targetOffsetY = { 300 }
        ),
        modifier = Modifier
            .align(BottomEnd)
            .padding(24.dp)
    ) {
        val scope = rememberCoroutineScope()
        FloatingActionButton(
            shape = CircleShape,
            containerColor = Color.White,
            onClick = {
                scope.launch {
                    scrollState.animateScrollToItem(0)
                }
            },
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_keyboard_arrow_up_24),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LazyColumn {
        items(3) {
            val pokemon = Pokemon(
                id = 1,
                name = "Name",
                height = 20,
                weight = 70,
                typeList = listOf(
                    TypeSimple(id = 13, name = "electric"),
                    TypeSimple(id = 9, name = "steel")
                ),
                sprite = "",
                statList = listOf(),
                abilityList = emptyList(),
                baseXp = 0
            )
            PokemonCard(pokemon = pokemon, onPokemonClick = {})
        }
    }
}