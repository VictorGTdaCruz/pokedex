package com.victor.feature_pokedex.presentation.ui.details.tabs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.victor.features_common.components.PokedexTextStyle

@Composable
internal fun TabCell(title: String, value: String? = null, description: @Composable (RowScope.() -> Unit)? = null) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = PokedexTextStyle.description,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(weight = 0.3f)
        )
        if (value != null)
            Text(
                text = value,
                style = PokedexTextStyle.body,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(weight = 0.7f)
            )
        else
            description?.invoke(this)
    }
}