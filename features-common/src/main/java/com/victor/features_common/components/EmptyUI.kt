package com.victor.features_common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.victor.features_common.R

@Composable
internal fun EmptyUI(message: String = stringResource(id = R.string.type_empty_message)) {
    MessageUI(
        message = message,
        imageRes = R.drawable.img_pikachu_resting
    )
}