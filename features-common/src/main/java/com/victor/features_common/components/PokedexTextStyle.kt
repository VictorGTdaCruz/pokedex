package com.victor.features_common.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.victor.features_common.theme.Gray

object PokedexTextStyle {

    val title = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.W400,
        color = Color.Black
    )

    val subtitle = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.W400,
        color = Color.Black
    )

    val body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        color = Gray
    )

    val description = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        color = Gray
    )

    fun TextStyle.bold() = copy(fontWeight = FontWeight.W700)
    fun TextStyle.noColor() = copy(color = Color.Unspecified)
}