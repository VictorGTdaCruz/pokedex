package com.victor.pokedex.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun WhiteText(
    text: String,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = fontSize,
        modifier = modifier
    )
}