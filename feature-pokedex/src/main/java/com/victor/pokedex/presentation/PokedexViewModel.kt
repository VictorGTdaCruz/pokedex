package com.victor.pokedex.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.domain.model.PokemonDetails
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

    var pokemonTypes = mutableStateOf<Resource>(Resource.Empty)
    var pokemons = mutableStateOf<Resource>(Resource.Empty)
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    fun loadPokemonTypes() {
        manageStates(pokemonTypes) {
            infrastructure.getPokemonTypes()
        }
    }

    fun loadPokemonsFromType(typeId: Long) {
        manageStates(pokemons) {
            infrastructure.getTypeDetails(typeId)
        }
    }

    fun loadPokemonDetails(pokemonId: Long) {
        viewModelScope.launch {
            val response = infrastructure.getPokemonDetails(pokemonId)
            pokemonDetails[response.id] = response
        }
    }
}

private fun <T> ViewModel.manageStates(
    mutableResource: MutableState<Resource>,
    request: suspend () -> T
) {
    viewModelScope.launch {
        mutableResource.value = Resource.Loading
        delay(2000)
        runCatching {
            val response = request()
            mutableResource.value = Resource.Success(response)
        }.getOrElse {
            mutableResource.value = Resource.Error(it.message ?: "")
        }
    }
}