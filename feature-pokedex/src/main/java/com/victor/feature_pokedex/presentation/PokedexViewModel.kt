package com.victor.feature_pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.feature_pokedex.domain.model.TypeDetails
import com.victor.feature_pokedex.domain.service.PokedexService
import com.victor.features_common.Resource
import com.victor.features_common.getAsSuccessResource
import com.victor.features_common.manageResourcesDuring
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
) : ViewModel() {

    var toolbarTitle by mutableStateOf("")

    val pokemonList = MutableStateFlow<PagingData<Pokemon>>(PagingData.empty())
    var pokemonTypes = mutableStateOf<Resource>(Resource.Empty)
    var typeDetails = mutableStateOf<Resource>(Resource.Empty)
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    fun loadPokemonList() {
        viewModelScope.launch {
            infrastructure.getPokemonList()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    pokemonList.value = it
                }
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