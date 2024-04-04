package com.victor.feature_pokedex.presentation.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.victor.feature_pokedex.R
import com.example.model.PokemonInformation
import com.victor.feature_pokedex.presentation.ui.components.PokemonColumn
import com.victor.feature_pokedex.presentation.ui.details.tabs.aboutTab
import com.victor.feature_pokedex.presentation.ui.details.tabs.evolutionTab
import com.victor.feature_pokedex.presentation.ui.details.tabs.statsTab
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold
import com.victor.features_common.observeState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun DetailsScreenBody(
    navController: NavController,
    viewModel: DetailsViewModel,
    pokemonId: Int,
    onPokemonClick: (Int) -> Unit
) {
    val tabs = listOf(
        stringResource(id = R.string.about_tab),
        stringResource(id = R.string.stats_tab),
        stringResource(id = R.string.evolutions_tab)
    )
    val pagerState = rememberPagerState { tabs.size }
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getPokemonInformation(pokemonId)
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    observeState<com.example.model.PokemonInformation>(
        state = viewModel.pokemonInformation,
        onSuccess = {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(TypeColorHelper.findBackground(it.typeList.firstOrNull()?.id))
            ) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                                tint = Color.White,
                                contentDescription = stringResource(id = R.string.content_description_back_button),
                            )
                        }
                    },
                    title = { }
                )
                Box(
                    Modifier.fillMaxWidth()
                ) {
                    Image(
                        painterResource(id = R.drawable.dotted_pattern),
                        contentDescription = null,
                        modifier = Modifier.align(BottomEnd)
                    )
                    Row(
                        Modifier.align(Center)
                    ) {
                        Box(
                            modifier = Modifier.size(125.dp)
                        ) {
                            Image(
                                painterResource(id = R.drawable.circle_for_pokemon_image),
                                contentDescription = stringResource(id = R.string.content_description_pokemon_image),
                            )
                            Image(
                                painter = rememberImagePainter(
                                    data = it.sprite,
                                    builder = {
                                        crossfade(true)
                                        crossfade(500)
                                    }
                                ),
                                contentDescription = stringResource(id = R.string.content_description_pokemon_image),
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        PokemonColumn(
                            id = it.id,
                            name = it.name,
                            typeList = it.typeList,
                            modifier = Modifier.align(CenterVertically)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = Color.Transparent,
                    indicator = { },
                    divider = { }
                ) {
                    tabs.forEachIndexed { index, item ->
                        Tab(
                            selected = selectedTabIndex == index,
                            text = {
                                Box {
                                    if (index == selectedTabIndex)
                                        Image(
                                            painterResource(id = R.drawable.home_toolbar_background),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .rotate(180f)
                                                .alpha(0.25f)
                                        )
                                    Text(
                                        text = item,
                                        color = Color.White,
                                        style = if (index == selectedTabIndex)
                                            PokedexTextStyle.body.bold()
                                        else
                                            PokedexTextStyle.body,
                                        modifier = Modifier.align(Center)
                                    )
                                }
                            },
                            onClick = {
                                selectedTabIndex = index
                            },
                            modifier = Modifier.clip(RoundedCornerShape(12.dp))
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxSize()
                ) { index ->
                    Box(
                        modifier = Modifier
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                            )
                            .fillMaxSize()
                    ) {
                        when (index) {
                            0 -> aboutTab(pokemonInformation = it)
                            1 -> statsTab(pokemonInformation = it)
                            2 -> evolutionTab(pokemonInformation = it, onPokemonClick)
                        }
                    }
                }
            }
        }
    )
}