package com.victor.feature_pokedex.presentation.ui.home.bottomsheets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
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
import com.victor.features_common.components.PokedexButton
import com.victor.features_common.components.PokedexButtonStyle
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SortBottomSheet(
    onDismiss: () -> Unit,
    onPokemonSortClick: (Sort) -> Unit,
    isSortButtonEnabled: (Sort) -> PokedexButtonStyle,
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
                text = stringResource(id = R.string.pokedex_sort_title),
                style = PokedexTextStyle.subtitle.bold(),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.pokedex_sort_description),
                style = PokedexTextStyle.body,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            PokedexButton(
                text = stringResource(id = R.string.pokedex_sort_option_1),
                onClick = { onPokemonSortClick(Sort.SmallestNumberFirst) },
                style = isSortButtonEnabled(Sort.SmallestNumberFirst),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PokedexButton(
                text = stringResource(id = R.string.pokedex_sort_option_2),
                onClick = { onPokemonSortClick(Sort.HighestNumberFirst) },
                style = isSortButtonEnabled(Sort.HighestNumberFirst),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PokedexButton(
                text = stringResource(id = R.string.pokedex_sort_option_3),
                onClick = { onPokemonSortClick(Sort.AtoZ) },
                style = isSortButtonEnabled(Sort.AtoZ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PokedexButton(
                text = stringResource(id = R.string.pokedex_sort_option_4),
                onClick = { onPokemonSortClick(Sort.ZtoA) },
                style = isSortButtonEnabled(Sort.ZtoA),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            )
            Spacer(
                modifier = Modifier.height(
                    36.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
            )
        }
    )
}

sealed class Sort {
    object SmallestNumberFirst : Sort()
    object HighestNumberFirst : Sort()
    object AtoZ : Sort()
    object ZtoA : Sort()
}