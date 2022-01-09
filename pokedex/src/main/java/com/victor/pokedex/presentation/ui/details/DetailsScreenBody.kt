package com.victor.pokedex.presentation.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.victor.pokedex.R
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.theme.Background

@Composable
internal fun DetailsScreenBody(
    viewModel: PokedexViewModel,
    pokemonId: Long,
    pokemonName: String
) {
    Surface(
        color = Background,
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.toolbarTitle = stringResource(
            id = R.string.details_screen_title,
            formatArgs = arrayOf(pokemonName.replaceFirstChar { it.titlecase() })
        )

        // TODO get details
    }
}