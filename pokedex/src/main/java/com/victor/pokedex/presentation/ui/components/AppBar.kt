package com.victor.pokedex.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.victor.pokedex.presentation.PokedexViewModel
import com.victor.pokedex.presentation.ui.theme.Background

@Composable
internal fun AppBar(
    navController: NavHostController,
    viewModel: PokedexViewModel
) {
    var hasPreviousBackStackEntry by remember { mutableStateOf(false) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        hasPreviousBackStackEntry = controller.previousBackStackEntry != null
    }

    val navigationIcon: (@Composable () -> Unit)? =
        if (hasPreviousBackStackEntry) {
            {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }

    TopAppBar(
        title = {
            Text(text = viewModel.toolbarTitle)
        },
        navigationIcon = navigationIcon,
        backgroundColor = Background,
        elevation = 0.dp
    )
}