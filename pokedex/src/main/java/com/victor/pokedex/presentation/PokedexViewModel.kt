package com.victor.pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.domain.model.PokemonSimplified
import com.victor.pokedex.domain.model.PokemonTypeSimplified
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
): ViewModel() {

    var toolbarTitle by mutableStateOf("")
    var isLoading by mutableStateOf(false)
        private set

    val pokemonTypes = mutableStateListOf<PokemonTypeSimplified>()
    var pokemons = mutableStateListOf<PokemonSimplified>()

    fun loadPokemonTypes() {
        if (pokemonTypes.isEmpty()) {
            viewModelScope.launch {
                isLoading = true

                delay(2000)
                val list = infrastructure.getPokemonTypes()
                pokemonTypes.addAll(list)

                isLoading = false
            }
        }
    }

    fun loadPokemonsFromType(typeId: Long) {
        viewModelScope.launch {
            isLoading = true

            delay(2000)
            val pokemonList = infrastructure.getPokemonType(typeId).pokemons
            pokemons.clear()
            pokemons.addAll(pokemonList)

            isLoading = false
        }
    }
}
