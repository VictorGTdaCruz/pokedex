package com.victor.feature_pokedex.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.feature_pokedex.R
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.presentation.ui.utils.TypeColorHelper
import com.victor.feature_pokedex.presentation.ui.utils.TypeDrawableHelper
import com.victor.features_common.components.PokedexTextStyle

@Composable
fun PokemonTypeBadge(
    type: PokemonType,
    iconSize: Dp = 24.dp,
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(TypeColorHelper.find(type.id))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(6.dp)
        ) {
            val drawableId = TypeDrawableHelper.find(type.id)
            Image(
                painter = painterResource(id = drawableId),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = stringResource(id = R.string.content_description_type_icon),
                modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = type.name.replaceFirstChar { it.titlecase() },
                style = PokedexTextStyle.description,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonTypeBadge(
        type = PokemonType(id = 16, name = "Dragon")
    )
}