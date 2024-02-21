package com.victor.feature_pokedex.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.feature_pokedex.R
import com.victor.features_common.components.PokedexTextStyle
import com.victor.features_common.components.PokedexTextStyle.bold
import com.victor.features_common.components.PokedexTextStyle.noColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeAppBar(
    onFilterClick: () -> Unit = {},
    onSortClick: () -> Unit = {},
    onGenerationClick: () -> Unit = {}
) {
    Box (
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.home_toolbar_background),
            contentDescription = "background_image",
        )
        LargeTopAppBar(
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            actions = {
                IconButton(
                    onClick = onGenerationClick,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_generation),
                        contentDescription = null,
                    )
                }
                IconButton(
                    onClick = onSortClick,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = null,
                    )
                }
                IconButton(
                    onClick = onFilterClick,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = null,
                    )
                }
            },
            title = {
                Column(
                    modifier = Modifier.padding(start = 12.dp, end = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_screen_title),
                        style = PokedexTextStyle.title.bold().noColor()
                    )
                    Text(
                        text = stringResource(id = R.string.home_screen_subtitle),
                        style = PokedexTextStyle.body,
                        lineHeight = 14.sp,
                    )
                }
            },
        )
    }
}

@Preview
@Composable
private fun Preview() {
    HomeAppBar()
}