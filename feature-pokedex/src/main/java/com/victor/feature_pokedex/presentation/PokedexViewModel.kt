package com.victor.feature_pokedex.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.feature_pokedex.domain.PokedexUseCase
import com.victor.feature_pokedex.domain.model.Pokemon
import com.victor.feature_pokedex.domain.model.PokemonDetails
import com.victor.feature_pokedex.domain.model.PokemonType
import com.victor.features_common.State
import com.victor.features_common.getAsSuccessResource
import com.victor.features_common.manageStateDuringRequest
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokedexUseCase
) : ViewModel() {

    val currentPokemonList = mutableStateOf<State>(State.Empty)
    private var fullPokemonList = emptyList<Pokemon>()
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()
    val pokemonTypes = mutableStateOf<State>(State.Empty)

    var searchText = mutableStateOf("")

    var showFilterBottomSheet = mutableStateOf(false)
    private val filteredTypes = mutableStateListOf<PokemonType>()
    var filteredRange = mutableStateOf(0f..0f)
    var maxRange = mutableStateOf(0f..0f)

    fun getPokemonList(
        typeList: List<PokemonType>? = null,
        indexRange: ClosedFloatingPointRange<Float>? = null
    ) {
        when {
            fullPokemonList.isEmpty() || typeList.isNullOrEmpty().not() || indexRange != null ->
                manageStateDuringRequest(
                    mutableState = currentPokemonList,
                    request = { useCase.getPokemonList(typeList, indexRange) },
                    onSuccess = { pokemonList ->
                        if (typeList.isNullOrEmpty()) {
                            fullPokemonList = pokemonList
                            if (indexRange == null) {
                                maxRange.value = 1f..pokemonList.maxOf { it.id }.toFloat()
                                filteredRange.value = maxRange.value
                            }
                        }
                    },
                )
            else -> currentPokemonList.value = State.Success(fullPokemonList)
        }
    }

    fun searchPokemon(text: String) {
        searchText.value = text
        val searchResult = when {
            text.isEmpty() -> fullPokemonList
            else -> fullPokemonList.filter {
                it.name.contains(text) || it.id.toString().contains(text)
            }
        }
        currentPokemonList.value = State.Success(searchResult)
    }

    fun isSearchEnabled() = currentPokemonList.value is State.Success<*>

    fun getPokemonDetails(pokemonId: Long) {
        viewModelScope.launch { // TODO sem tratamento?
            runCatching {
                val response = useCase.getPokemonDetails(pokemonId)
                pokemonDetails[response.id] = response
            }
        }
    }

    fun getPokemonTypes() {
        val types = pokemonTypes.getAsSuccessResource<List<PokemonType>>()?.data
        if (types.isNullOrEmpty()) {
            manageStateDuringRequest(pokemonTypes) {
                useCase.getPokemonTypes()
            }
        }
    }

    fun onFilterIconClick() {
        showFilterBottomSheet.value = !showFilterBottomSheet.value
    }

    fun onDismissFilterBottomSheet() {
        showFilterBottomSheet.value = false
    }

    fun isPokemonTypeFilterIconFilled(type: PokemonType) = filteredTypes.contains(type)

    fun onPokemonTypeFilterIconClick(type: PokemonType) {
        filteredTypes.apply {
            if (contains(type)) remove(type) else add(type)
        }
    }

    fun onRangeFilterUpdate(range: ClosedFloatingPointRange<Float>) {
        filteredRange.value = range
    }

    fun onPokemonTypeFilterResetClick() {
        filteredTypes.clear()
        filteredRange.value = maxRange.value
    }

    fun onPokemonTypeFilterApplyClick() {
        onDismissFilterBottomSheet()
        getPokemonList(filteredTypes, filteredRange.value)
    }
}