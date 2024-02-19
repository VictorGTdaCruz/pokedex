package com.victor.feature_pokedex.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.victor.feature_pokedex.R
import com.victor.features_common.components.PokedexButtonStyle
import com.victor.features_common.components.PokedexTextStyle

@Composable
fun GenerationButton(
    text: String,
    onClick: () -> Unit,
    style: PokedexButtonStyle = PokedexButtonStyle.Primary,
    imageId: Int? = null
) {
    Box {

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = style.backgroundColor
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = style.elevation
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.height(if (imageId == null) 50.dp else 128.dp),
        ) {
            Column {
                if (imageId != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(140.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Text(
                    text = text,
                    style = PokedexTextStyle.body,
                    color = style.textColor,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.generation_button_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}