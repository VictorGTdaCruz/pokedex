package com.victor.pokedex.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.features_common.Resource
import com.victor.features_common.getAsSuccessResource
import com.victor.features_common.manageResourcesDuring
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.model.TypeDetails
import com.victor.pokedex.domain.service.PokedexService
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val infrastructure: PokedexService
) : ViewModel() {

    var toolbarTitle by mutableStateOf("")

    var pokemonTypes = mutableStateOf<Resource>(Resource.Empty)
    var typeDetails = mutableStateOf<Resource>(Resource.Empty)
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

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