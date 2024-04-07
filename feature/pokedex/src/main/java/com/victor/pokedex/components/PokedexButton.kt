package com.victor.pokedex.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.victor.pokedex.theme.Gray
import com.victor.pokedex.theme.LightGray
import com.victor.pokedex.theme.Red

sealed class PokedexButtonStyle(
    val backgroundColor: Color,
    val textColor: Color,
    val elevation: Dp,
) {
    object Primary :
        PokedexButtonStyle(backgroundColor = Red, textColor = Color.White, elevation = 4.dp)
    object Secondary :
        PokedexButtonStyle(backgroundColor = LightGray, textColor = Gray, elevation = 0.dp)
}

@Composable
fun PokedexButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: PokedexButtonStyle = PokedexButtonStyle.Primary,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = style.backgroundColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = style.elevation
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(50.dp),
    ) {
        Text(
            text = text,
            style = PokedexTextStyle.body,
            color = style.textColor
        )
    }
}

@Preview
@Composable
fun pokedexButtonPreview() {
    Box(
        Modifier.background(Color.White).padding(24.dp)
    ) {
        PokedexButton(text = "Test", onClick = {})
    }
}