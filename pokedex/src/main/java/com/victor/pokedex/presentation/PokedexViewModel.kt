package com.victor.pokedex.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.domain.model.PokemonTypeSimplified
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
): ViewModel() {

    var pokemonTypes = mutableStateListOf<PokemonTypeSimplified>()
        private set

    fun loadPokemonTypes() {
        viewModelScope.runCatching {
            launch {

                val list = infrastructure.getPokemonTypes()
                pokemonTypes.addAll(list)
            }
        }
    }
}
