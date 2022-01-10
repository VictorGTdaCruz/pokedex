package com.victor.pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.features_common.Resource
import com.victor.features_common.manageResources
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
) : ViewModel() {

    var toolbarTitle by mutableStateOf("")

    var pokemonTypes = mutableStateOf<Resource>(Resource.Empty)
    var pokemons = mutableStateOf<Resource>(Resource.Empty)
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    fun loadPokemonTypes() {
        manageResources(pokemonTypes) {
            infrastructure.getPokemonTypes()
        }
    }

    fun loadPokemonsFromType(typeId: Long) {
        manageResources(pokemons) {
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