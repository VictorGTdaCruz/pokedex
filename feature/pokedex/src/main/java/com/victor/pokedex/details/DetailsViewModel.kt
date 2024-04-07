package com.victor.pokedex.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.victor.domain.PokemonInformationUseCase
import com.victor.pokedex.utils.State
import com.victor.pokedex.utils.manageStateDuringRequest

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