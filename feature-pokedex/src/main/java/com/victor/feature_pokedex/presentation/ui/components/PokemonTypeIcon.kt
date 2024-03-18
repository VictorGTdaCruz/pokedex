package com.victor.feature_pokedex.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.TypeDrawableHelper

@Composable
fun PokemonTypeIcon(
    type: PokemonType,
    onClick: ((PokemonType) -> Unit)? = null,
    isFilled: Boolean = true,
    cardPadding: Dp = 8.dp,
    iconSize: Dp = 24.dp,
    iconPadding: Dp = 8.dp,
) {
    val typeColor = TypeColorHelper.find(type.id)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isFilled) typeColor else Color.Transparent
        ),
        modifier = Modifier
            .padding(cardPadding)
            .clickable { onClick?.invoke(type) }
    ) {
        Image(
            painter = painterResource(id = TypeDrawableHelper.find(type.id)),
            colorFilter = ColorFilter.tint(
                if (isFilled) Color.White else typeColor
            ),
            contentDescription = stringResource(id = R.string.content_description_type_icon),
            modifier = Modifier
                .padding(iconPadding)
                .size(iconSize)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonTypeIcon(
        type = PokemonType(id = 16, name = "Dragon"),
        onClick = {},
    )
}