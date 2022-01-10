package com.victor.pokedex.presentation.ui.components

import androidx.compose.runtime.Composable
import com.victor.pokedex.R

@Composable
internal fun EmptyUI(message: String) {
    MessageUI(
        message = message,
        imageRes = R.drawable.img_pikachu_resting
    )
}