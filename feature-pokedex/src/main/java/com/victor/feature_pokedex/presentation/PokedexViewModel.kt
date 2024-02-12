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
import com.victor.features_common.State
import com.victor.features_common.getAsSuccessResource
import com.victor.features_common.manageStateDuringRequest
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
) : ViewModel() {

    var toolbarTitle by mutableStateOf("")

    val currentPokemonList = mutableStateOf<State>(State.Empty)
    private var fullPokemonList = emptyList<Pokemon>()
    var pokemonTypes = mutableStateOf<State>(State.Empty)
    var typeDetails = mutableStateOf<State>(State.Empty)
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    fun loadPokemonList() {
        val pokemonListData = currentPokemonList.getAsSuccessResource<List<Pokemon>>()?.data
        if (pokemonListData.isNullOrEmpty()) {
            manageStateDuringRequest(
                mutableState = currentPokemonList,
                request = { infrastructure.getPokemonList() },
                onSuccess = { fullPokemonList = it },
            )
        }
    }

    fun searchPokemon(text: String) {
        val searchResult = when {
            text.isEmpty() -> fullPokemonList
            else -> fullPokemonList.filter {
                it.name.contains(text) || it.id.toString().contains(text)
            }
        }
        currentPokemonList.value = State.Success(searchResult)
    }

    fun loadPokemonTypes() {
        val types = pokemonTypes.getAsSuccessResource<List<PokemonType>>()?.data
        if (types.isNullOrEmpty()) {
            manageStateDuringRequest(pokemonTypes) {
                infrastructure.getPokemonTypes()
            }
        }
    }

    fun loadPokemonsFromType(typeId: Long) {
        val details = typeDetails.getAsSuccessResource<TypeDetails>()?.data
        if (details?.id != typeId) {
            manageStateDuringRequest(typeDetails) {
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