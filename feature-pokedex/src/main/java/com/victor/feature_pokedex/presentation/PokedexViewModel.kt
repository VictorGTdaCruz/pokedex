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
import com.victor.feature_pokedex.presentation.ui.home.bottomsheets.Sort
import com.victor.features_common.State
import com.victor.features_common.components.PokedexButtonStyle
import com.victor.features_common.getAsSuccessState
import com.victor.features_common.manageStateDuringRequest
import kotlinx.coroutines.launch

internal class PokedexViewModel(
    private val useCase: PokedexUseCase
) : ViewModel() {

    val currentPokemonList = mutableStateOf<State>(State.Empty)
    private var fullPokemonList = emptyList<Pokemon>()
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    var searchText = mutableStateOf("")

    var showFilterBottomSheet = mutableStateOf(false)
    val pokemonTypes = mutableStateOf<State>(State.Empty)
    private val filteredTypes = mutableStateListOf<PokemonType>()
    var filteredRange = mutableStateOf(0f..0f)
    var maxRange = mutableStateOf(0f..0f)

    var showSortBottomSheet = mutableStateOf(false)
    private var currentSort = mutableStateOf<Sort>(Sort.SmallestNumberFirst)

    var showGenerationBottomSheet = mutableStateOf(false)
    val generations = 1..8
    private val selectedGenerations = mutableStateListOf<Int>()

    fun getPokemonList() {
        manageStateDuringRequest(
            mutableState = currentPokemonList,
            request = {
                useCase.getPokemonList(filteredTypes, filteredRange.value, selectedGenerations)
            },
            onSuccess = { pokemonList ->
                if (filteredTypes.isEmpty() && filteredRange.value == 0f..0f && selectedGenerations.isEmpty()) {
                    fullPokemonList = pokemonList
                    if (filteredRange.value == 0f..0f) {
                        maxRange.value = 1f..pokemonList.maxOf { it.id }.toFloat()
                        filteredRange.value = maxRange.value
                    }
                }
            },
        )
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
        val types = pokemonTypes.getAsSuccessState<List<PokemonType>>()?.data
        if (types.isNullOrEmpty()) {
            manageStateDuringRequest(pokemonTypes) {
                useCase.getPokemonTypes()
            }
        }
    }

    fun onFilterIconClick() {
        showFilterBottomSheet.value = !showFilterBottomSheet.value
    }

    fun onSortIconClick() {
        showSortBottomSheet.value = !showSortBottomSheet.value
    }

    fun onGenerationIconClick() {
        showGenerationBottomSheet.value = !showGenerationBottomSheet.value
    }

    fun onDismissBottomSheet() {
        showFilterBottomSheet.value = false
        showSortBottomSheet.value = false
        showGenerationBottomSheet.value = false
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
        onDismissBottomSheet()
        getPokemonList()
    }

    fun onPokemonSortClick(sort: Sort) {
        currentSort.value = sort
        val currentList = currentPokemonList.getAsSuccessState<List<Pokemon>>()?.data
        val result = when (sort) {
            Sort.SmallestNumberFirst -> currentList?.sortedBy { it.id }
            Sort.HighestNumberFirst -> currentList?.sortedByDescending { it.id }
            Sort.AtoZ -> currentList?.sortedBy { it.name }
            Sort.ZtoA -> currentList?.sortedByDescending { it.name }
        }
        currentPokemonList.value = State.Success(result)
    }

    fun isSortButtonEnabled(sort: Sort) =
        if (currentSort.value == sort) PokedexButtonStyle.Primary else PokedexButtonStyle.Secondary

    fun onPokemonGenerationClick(generation: Int) {
        selectedGenerations.apply {
            if (contains(generation)) remove(generation) else add(generation)
        }
        getPokemonList()
    }

    fun isGenerationButtonEnabled(generation: Int) =
        if (selectedGenerations.contains(generation)) PokedexButtonStyle.Primary else PokedexButtonStyle.Secondary
}