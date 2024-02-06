package com.victor.feature_pokedex.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.feature_pokedex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppBar() {
    Box (
        modifier = Modifier.fillMaxWidth().height(150.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.home_toolbar_background),
            contentDescription = "background_image",
            contentScale = ContentScale.Crop
        )
        LargeTopAppBar(
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            title = {
                Column(
                    modifier = Modifier.padding(end = 32.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_screen_title),
                        fontWeight = FontWeight.W700,
                        fontSize = 28.sp
                    )
                    Text(
                        text = stringResource(id = R.string.home_screen_subtitle),
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
//                        color = Gray, //TODO for some reason using a color makes the text appear twice ???
                    )
                }
            },
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppBar()
}