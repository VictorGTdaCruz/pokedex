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
    val pokemonDetails = mutableStateMapOf<Long, PokemonDetails>()

    var searchText = mutableStateOf("")
    private var fullPokemonList = emptyList<Pokemon>()

    var showFilterBottomSheet = mutableStateOf(false)
    val pokemonTypes = mutableStateOf<State>(State.Empty)
    private val selectedTypes = mutableStateListOf<PokemonType>()
    var selectedIdRange = mutableStateOf<ClosedFloatingPointRange<Float>?>(null)
    var fullIdRange = mutableStateOf<ClosedFloatingPointRange<Float>?>(null)

    var showSortBottomSheet = mutableStateOf(false)
    private var selectedSort = mutableStateOf<Sort>(Sort.SmallestNumberFirst)

    var showGenerationBottomSheet = mutableStateOf(false)
    private val selectedGeneration = mutableStateOf<Int?>(null)

    fun getPokemonList() {
        manageStateDuringRequest(
            mutableState = currentPokemonList,
            request = {
                useCase.getPokemonList(
                    selectedTypes,
                    selectedGeneration.value,
                    selectedIdRange.value,
                    selectedSort.value,
                )
            },
            onSuccess = { pokemonList ->
                if (selectedTypes.isEmpty() && selectedIdRange.value == null && selectedGeneration.value == null) {
                    fullPokemonList = pokemonList
                    fullIdRange.value = 1f..pokemonList.maxOf { it.id }.toFloat()
                    selectedIdRange.value = fullIdRange.value
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

    fun isPokemonTypeFilterIconFilled(type: PokemonType) = selectedTypes.contains(type)

    fun onPokemonTypeFilterIconClick(type: PokemonType) {
        selectedTypes.apply {
            if (contains(type)) remove(type) else add(type)
        }
    }

    fun onRangeFilterUpdate(range: ClosedFloatingPointRange<Float>) {
        selectedIdRange.value = range
    }

    fun onPokemonTypeFilterResetClick() {
        selectedTypes.clear()
        selectedIdRange.value = fullIdRange.value
    }

    fun onPokemonTypeFilterApplyClick() {
        onDismissBottomSheet()
        getPokemonList()
    }

    fun onPokemonSortClick(sort: Sort) {
        selectedSort.value = sort
        val currentList = currentPokemonList.getAsSuccessState<List<Pokemon>>()?.data
        if (currentList != null)
            currentPokemonList.value = State.Success(useCase.sort(currentList, sort))
    }

    fun isSortButtonEnabled(sort: Sort) =
        if (selectedSort.value == sort) PokedexButtonStyle.Primary else PokedexButtonStyle.Secondary

    fun onPokemonGenerationClick(generation: Int) {
        selectedGeneration.value = if (selectedGeneration.value == generation) null else generation
        getPokemonList()
    }

    fun isGenerationButtonEnabled(generation: Int) =
        if (selectedGeneration.value == generation) PokedexButtonStyle.Primary else PokedexButtonStyle.Secondary
}