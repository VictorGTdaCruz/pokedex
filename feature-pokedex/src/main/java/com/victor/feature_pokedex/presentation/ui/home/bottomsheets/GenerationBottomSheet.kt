package com.victor.feature_pokedex.presentation.ui.home.bottomsheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.PokemonListUseCase
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.components.GenerationButton
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GenerationBottomSheet(viewModel: PokedexViewModel) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.onDismissBottomSheet() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Color.White,
        windowInsets = WindowInsets(
            top = WindowInsets.navigationBars.asPaddingValues().calculateTopPadding(),
            bottom = WindowInsets.statusBars.asPaddingValues().calculateBottomPadding()
        ),
        content = {
            Text(
                text = stringResource(id = R.string.pokedex_generation_title),
                style = PokedexTextStyle.subtitle.bold(),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.pokedex_generation_description),
                style = PokedexTextStyle.body,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(PokemonListUseCase.SELECTABLE_POKEMON_GENERATION_RANGE.count()) {
                        val generation = it + 1
                        GenerationButton(
                            text = stringResource(id = R.string.pokedex_generation_option, generation),
                            onClick = { viewModel.onPokemonGenerationClick(generation) },
                            style = viewModel.isGenerationButtonEnabled(generation),
                            imageId = getGenerationDrawable(generation),
                        )
                    }
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(
                modifier = Modifier.height(
                    36.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
            )
        }
    )
}

private fun getGenerationDrawable(generation: Int) = when (generation) {
    2 -> R.drawable.generation_2
    3 -> R.drawable.generation_3
    4 -> R.drawable.generation_4
    5 -> R.drawable.generation_5
    6 -> R.drawable.generation_6
    7 -> R.drawable.generation_7
    8 -> R.drawable.generation_8
    else -> R.drawable.generation_1
}