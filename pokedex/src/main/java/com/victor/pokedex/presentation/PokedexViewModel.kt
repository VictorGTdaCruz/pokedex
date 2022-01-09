package com.victor.pokedex.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.domain.model.PokemonTypeSimplified
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
): ViewModel() {

    var pokemonTypes = mutableStateListOf<PokemonTypeSimplified>()
        private set

    val isLoading = mutableStateOf(false)

    fun loadPokemonTypes() {
        if (pokemonTypes.isEmpty()) {
            viewModelScope.launch {
                isLoading.value = true

                delay(2000)
                val list = infrastructure.getPokemonTypes()
                pokemonTypes.addAll(list)

                isLoading.value = false
            }
        }
    }
}
