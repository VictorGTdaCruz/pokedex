package com.victor.pokedex.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.victor.pokedex.R

@Composable
fun MessageUI(
    message: String,
    imageRes: Int = R.drawable.img_pikachu_resting,
    reload: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = R.string.content_description_feedback_image),
            modifier = Modifier
                .size(150.dp)
        )
        Text(
            text = message,
            style = PokedexTextStyle.body,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        if (reload != null)
            PokedexButton(
                text = stringResource(id = R.string.error_button),
                onClick = reload,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
    }
}

@Preview
@Composable
private fun Preview() {
    MessageUI(
        message = "Error",
        reload = {}
    )
}