package com.victor.feature_pokedex.presentation.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.victor.feature_pokedex.domain.PokemonInformationUseCase
import com.victor.feature_pokedex.domain.PokemonListUseCase
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.features_common.State
import com.victor.features_common.manageStateDuringRequest

internal class DetailsViewModel(
    private val pokemonInformationUseCase: PokemonInformationUseCase,
) : ViewModel() {

    val pokemonInformation = mutableStateOf<State>(State.Empty)

    fun getPokemonInformation(pokemonId: Int) {
        manageStateDuringRequest(
            mutableState = pokemonInformation,
        ) {
            pokemonInformationUseCase.getPokemonInformation(pokemonId)
        }
    }
}