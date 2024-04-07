package com.victor.pokedex.home.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victor.pokedex.R
import com.victor.pokedex.components.PokemonTypeIcon
import com.victor.pokedex.utils.State
import com.victor.pokedex.components.PokedexButton
import com.victor.pokedex.components.PokedexButtonStyle
import com.victor.pokedex.components.PokedexTextStyle
import com.victor.pokedex.components.PokedexTextStyle.bold
import com.victor.pokedex.utils.observeStateInsideLazyList
import com.victor.pokedex.theme.Red
import com.victor.model.TypeSimple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilterBottomSheet(
    typeListState: MutableState<State>,
    selectedIdRangeState: ClosedFloatingPointRange<Float>,
    fullIdRangeState: ClosedFloatingPointRange<Float>,
    onDismiss: () -> Unit,
    isPokemonTypeFilterIconFilled: (TypeSimple) -> Boolean,
    onPokemonTypeFilterIconClick: (TypeSimple) -> Unit,
    onPokemonTypeFilterResetClick: () -> Unit,
    onPokemonTypeFilterApplyClick: () -> Unit,
    onRangeFilterUpdate: (ClosedFloatingPointRange<Float>) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Color.White,
        windowInsets = WindowInsets(
            top = WindowInsets.navigationBars.asPaddingValues().calculateTopPadding(),
            bottom = WindowInsets.statusBars.asPaddingValues().calculateBottomPadding()
        ),
        content = {
            Text(
                text = stringResource(id = R.string.pokedex_filter_title),
                style = PokedexTextStyle.subtitle.bold(),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.pokedex_filter_description),
                style = PokedexTextStyle.body,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.pokedex_filter_type_label),
                style = PokedexTextStyle.body.bold(),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 24.dp),
            )
            LazyRow {
                item { Spacer(modifier = Modifier.width(16.dp)) }
                observeStateInsideLazyList<List<TypeSimple>>(state = typeListState) { typeList ->
                    items(typeList.size) { index ->
                        val type = typeList[index]
                        PokemonTypeIcon(
                            type = type,
                            isFilled = isPokemonTypeFilterIconFilled(type),
                            onClick = onPokemonTypeFilterIconClick,
                        )
                    }
                }
                item { Spacer(modifier = Modifier.width(24.dp)) }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.pokedex_filter_number_range_label),
                style = PokedexTextStyle.body.bold(),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 24.dp),
            )
            RangeSlider(
                value = selectedIdRangeState,
                valueRange = fullIdRangeState,
                onValueChange = onRangeFilterUpdate,
                modifier = Modifier.padding(horizontal = 24.dp),
                colors = SliderDefaults.colors(
                    activeTickColor = Red,
                    activeTrackColor = Red
                ),
                startThumb = { SliderThumb(it.activeRangeStart.toInt().toString()) },
                endThumb = { SliderThumb(it.activeRangeEnd.toInt().toString()) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(start = 24.dp, end = 24.dp, bottom = 36.dp)
            ) {
                PokedexButton(
                    text = stringResource(id = R.string.pokedex_filter_type_reset_button),
                    onClick = onPokemonTypeFilterResetClick,
                    style = PokedexButtonStyle.Secondary,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                PokedexButton(
                    text = stringResource(id = R.string.pokedex_filter_type_apply_button),
                    onClick = onPokemonTypeFilterApplyClick,
                    style = PokedexButtonStyle.Primary,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()))
        }
    )
}

@Composable
fun SliderThumb(text: String) {
    Column(
        Modifier.padding(top = 18.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Red)
                .size(20.dp)
                .align(CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(12.dp)
                    .align(Center)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            style = PokedexTextStyle.description,
            modifier = Modifier.align(CenterHorizontally)
        )
    }
}