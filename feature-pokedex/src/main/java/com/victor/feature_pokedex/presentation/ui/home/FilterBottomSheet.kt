package com.victor.feature_pokedex.presentation.ui.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.presentation.PokedexViewModel
import com.victor.feature_pokedex.presentation.ui.components.PokemonTypeIcon
import com.victor.features_common.ObserveState
import com.victor.features_common.components.PokedexButton
import com.victor.features_common.components.PokedexButtonStyle
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilterBottomSheet(viewModel: PokedexViewModel) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.onDismissFilterBottomSheet() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Color.White,
        content = {
            Text(
                text = stringResource(id = R.string.pokedex_filter_type_title),
                style = PokedexTextStyle.subtitle.bold(),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.pokedex_filter_type_description),
                style = PokedexTextStyle.body,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.pokedex_filter_type_type_label),
                style = PokedexTextStyle.body.bold(),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 24.dp),
            )
            ObserveState<List<PokemonType>>(state = viewModel.pokemonTypes) { typeList ->
                LazyRow {
                    item { Spacer(modifier = Modifier.width(16.dp)) }
                    items(typeList.size) { index ->
                        val type = typeList[index]
                        PokemonTypeIcon(
                            type = type,
                            isFilled = viewModel.isPokemonTypeFilterIconFilled(type),
                            onClick = { viewModel.onPokemonTypeFilterIconClick(it) },
                        )
                    }
                    item { Spacer(modifier = Modifier.width(24.dp)) }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(start = 24.dp, end = 24.dp, bottom = 48.dp)
            ) {
                PokedexButton(
                    text = stringResource(id = R.string.pokedex_filter_type_reset_button),
                    onClick = { viewModel.onPokemonTypeFilterResetClick() },
                    style = PokedexButtonStyle.Secondary,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                PokedexButton(
                    text = stringResource(id = R.string.pokedex_filter_type_apply_button),
                    onClick = { viewModel.onPokemonTypeFilterApplyClick() },
                    style = PokedexButtonStyle.Primary,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    )
}