package com.victor.feature_pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.TypeDetails
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.features_common.Resource
import com.victor.features_common.getAsSuccessResource
import com.victor.features_common.manageResourcesDuring
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
) : ViewModel() {

    var toolbarTitle by mutableStateOf("")

    val currentPokemonList = mutableStateOf<Resource>(Resource.Empty)
    private var fullPokemonList = emptyList<Pokemon>()
    var pokemonTypes = mutableStateOf<Resource>(Resource.Empty)
    var typeDetails = mutableStateOf<Resource>(Resource.Empty)
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    fun loadPokemonList() {
        val pokemonListData = currentPokemonList.getAsSuccessResource<List<Pokemon>>()?.data
        if(pokemonListData.isNullOrEmpty()) {
            manageResourcesDuring(currentPokemonList) {
                infrastructure.getPokemonList()
            }
        }
    }

    fun searchPokemon(text: String) {
        if (text.isEmpty() && fullPokemonList.isNotEmpty()) {
            currentPokemonList.value = Resource.Success(fullPokemonList)
            return
        }
        val currentList = currentPokemonList.getAsSuccessResource<List<Pokemon>>()?.data
        if(currentList != null) {
            if (currentList.size > fullPokemonList.size) fullPokemonList = currentList
            val filteredPokemonList = fullPokemonList.filter {
                it.name.contains(text) || it.id.toString().contains(text)
            }
            currentPokemonList.value = Resource.Success(filteredPokemonList)
        }
    }

    fun loadPokemonTypes() {
        val types = pokemonTypes.getAsSuccessResource<List<PokemonType>>()?.data
        if (types.isNullOrEmpty()) {
            manageResourcesDuring(pokemonTypes) {
                infrastructure.getPokemonTypes()
            }
        }
    }

    fun loadPokemonsFromType(typeId: Long) {
        val details = typeDetails.getAsSuccessResource<TypeDetails>()?.data
        if (details?.id != typeId) {
            manageResourcesDuring(typeDetails) {
                infrastructure.getTypeDetails(typeId)
            }
        }
    }

    fun loadPokemonDetails(pokemonId: Long) {
        viewModelScope.launch {
            runCatching {
                val response = infrastructure.getPokemonDetails(pokemonId)
                pokemonDetails[response.id] = response
            }
        }
    }
}