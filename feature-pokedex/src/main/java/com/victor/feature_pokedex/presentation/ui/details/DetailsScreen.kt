package com.victor.feature_pokedex.presentation.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.components.PokemonDetailsColumn
import com.victor.feature_pokedex.presentation.ui.details.tabs.aboutTab
import com.victor.feature_pokedex.presentation.ui.details.tabs.evolutionTab
import com.victor.feature_pokedex.presentation.ui.details.tabs.statsTab
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun DetailsScreenBody(navController: NavController, viewModel: PokedexViewModel, pokemonId: Long) {
    val pokemonDetails = viewModel.pokemonDetails[pokemonId]
    val tabs = listOf(
        PokedexTabItem("About") { aboutTab(pokemonDetails = pokemonDetails) },
        PokedexTabItem("Stats") { statsTab(pokemonDetails = pokemonDetails) },
        PokedexTabItem("Evolution") { evolutionTab(pokemonDetails = pokemonDetails) },
    )
    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(TypeColorHelper.findBackground(pokemonDetails?.types?.first()?.type?.id))
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        tint = Color.White,
                        contentDescription = stringResource(id = R.string.content_description_back_button),
                    )
                }
            },
            title = { }
        )
        Row(
            Modifier.align(CenterHorizontally)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = pokemonDetails?.sprites?.otherFrontDefault,
                    builder = {
                        crossfade(true)
                        crossfade(500)
                    }
                ),
                contentDescription = stringResource(id = R.string.content_description_pokemon_image),
                modifier = Modifier.size(125.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (pokemonDetails != null)
                PokemonDetailsColumn(
                    pokemonDetails = pokemonDetails,
                    modifier = Modifier.align(CenterVertically)
                )
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
                        Text(
                            text = item.name,
                            color = Color.White,
                            style = if (index == selectedTabIndex)
                                PokedexTextStyle.body.bold()
                            else
                                PokedexTextStyle.body,
                        )
                    },
                    onClick = {
                        selectedTabIndex = index
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
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
                tabs[index].content.invoke()
            }
        }
    }
}

data class PokedexTabItem(
    val name: String,
    val content: @Composable () -> Unit
)