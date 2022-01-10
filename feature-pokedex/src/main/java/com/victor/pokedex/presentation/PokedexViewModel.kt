package com.victor.pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.domain.model.Pokemon
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
) : ViewModel() {

    var toolbarTitle by mutableStateOf("")
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf("")
        private set

    val pokemonTypes = mutableStateListOf<PokemonType>()
    val pokemons = mutableStateListOf<Pokemon>()
    val details = mutableStateMapOf<Long, PokemonDetails>()

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
            val pokemonList = infrastructure.getTypeDetails(typeId).pokemons
            pokemons.clear()
            pokemons.addAll(pokemonList)

            isLoading = false
        }
    }

    fun loadPokemonDetails(pokemonId: Long) {
        viewModelScope.launch {
            val response = infrastructure.getPokemonDetails(pokemonId)
            details[response.id] = response
        }
    }
}