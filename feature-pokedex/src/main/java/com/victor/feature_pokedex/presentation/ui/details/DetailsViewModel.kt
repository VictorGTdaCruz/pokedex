package com.victor.feature_pokedex.presentation.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.victor.feature_pokedex.domain.PokedexUseCase
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.features_common.State
import com.victor.features_common.manageStateDuringRequest

internal class DetailsViewModel(
    private val details: PokemonDetails,
    private val useCase: PokedexUseCase,
) : ViewModel() {

    private val pokemonSpecies = mutableStateOf<State>(State.Empty)

    fun getPokemonInformation() {
        manageStateDuringRequest(
            mutableState = pokemonSpecies,
        ) {
            useCase.getPokemonSpecies(details.id)
        }
    }
}