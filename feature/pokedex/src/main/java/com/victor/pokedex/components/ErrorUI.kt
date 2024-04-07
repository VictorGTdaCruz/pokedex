package com.victor.pokedex.components

import androidx.compose.runtime.Composable
import com.victor.pokedex.R

@Composable
fun ErrorUI(
    message: String,
    reload: () -> Unit
) {
    MessageUI(
        message = message,
        imageRes = R.drawable.img_pikachu_sad,
        reload = reload
    )
}