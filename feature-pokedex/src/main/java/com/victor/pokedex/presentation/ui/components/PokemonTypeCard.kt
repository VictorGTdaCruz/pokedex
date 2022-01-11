package com.victor.pokedex.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.pokedex.presentation.ui.utils.TypeDrawableHelper

@Composable
fun PokemonTypeCard(
    type: PokemonType,
    onTypeClick: (PokemonType) -> Unit,
    cardPadding: Dp = 8.dp,
    iconSize: Dp = 24.dp,
    iconPadding: Dp = 8.dp,
    fontSize: TextUnit = 14.sp,
    fontPadding: Dp = 8.dp,
) {
    Card(
        backgroundColor = TypeColorHelper.find(type.id),
        modifier = Modifier
            .padding(cardPadding)
            .clickable { onTypeClick(type) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            val drawableId = TypeDrawableHelper.find(type.id)
            Image(
                painter = painterResource(id = drawableId),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "type_bug",
                modifier = Modifier
                    .padding(iconPadding)
                    .size(iconSize)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = type.name.replaceFirstChar { it.titlecase() },
                color = Color.White,
                fontSize = fontSize,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(fontPadding)
            )
        }
    }
}