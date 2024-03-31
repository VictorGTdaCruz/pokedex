package com.victor.feature_pokedex.presentation.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.victor.feature_pokedex.domain.PokedexUseCase
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.features_common.State

internal class DetailsViewModel(
    private val details: Pokemon,
    private val useCase: PokedexUseCase,
) : ViewModel() {

    private val pokemonSpecies = mutableStateOf<State>(State.Empty)

//    fun getPokemonInformation() {
//        manageStateDuringRequest(
//            mutableState = pokemonSpecies,
//        ) {
//            useCase.getPokemonInformation(details.id)
//        }
//    }
}