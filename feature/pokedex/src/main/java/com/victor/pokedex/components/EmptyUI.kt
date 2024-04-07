package com.victor.pokedex.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.victor.pokedex.R

@Composable
internal fun EmptyUI(message: String = stringResource(id = R.string.type_empty_message)) {
    MessageUI(
        message = message,
        imageRes = R.drawable.img_pikachu_resting
    )
}