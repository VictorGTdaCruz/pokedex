package com.victor.pokedex.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.data.repository.PokemonRepository
import com.victor.data.repository.TypeRepository
import com.victor.domain.PokemonListUseCase
import com.victor.domain.Sort
import com.victor.pokedex.utils.State
import com.victor.pokedex.components.PokedexButtonStyle
import com.victor.pokedex.utils.getAsSuccessState
import com.victor.pokedex.utils.manageStateDuringRequest
import com.victor.model.Pokemon
import com.victor.model.PokemonSimple
import com.victor.model.TypeSimple
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val pokemonListUseCase: PokemonListUseCase,
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository,
) : ViewModel() {

    private var fullPokemonList = emptyList<PokemonSimple>()
    val currentPokemonList = mutableStateOf<State>(State.Empty)
    val pokemon = mutableStateMapOf<Int, Pokemon>()

    val searchText = mutableStateOf("")

    val pokemonTypes = mutableStateOf<State>(State.Empty)
    private val selectedTypes = mutableStateListOf<TypeSimple>()
    val selectedIdRange = mutableStateOf<ClosedFloatingPointRange<Float>?>(null)
    val fullIdRange = mutableStateOf<ClosedFloatingPointRange<Float>?>(null)
    private val selectedSort = mutableStateOf<Sort>(Sort.SmallestNumberFirst)
    private val selectedGeneration = mutableStateOf<Int?>(null)

    fun getPokemonList() {
        manageStateDuringRequest(
            mutableState = currentPokemonList,
            request = {
                pokemonListUseCase.getPokemonList(
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

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch { // TODO sem tratamento?
            runCatching {
                val response = pokemonRepository.getPokemon(pokemonId)
                pokemon[response.id] = response
            }
        }
    }

    fun getTypeList() {
        val typeList = pokemonTypes.getAsSuccessState<List<TypeSimple>>()?.data
        if (typeList.isNullOrEmpty()) {
            manageStateDuringRequest(pokemonTypes) {
                typeRepository.getTypeList()
            }
        }
    }

    fun isPokemonTypeFilterIconFilled(type: TypeSimple) = selectedTypes.contains(type)

    fun onPokemonTypeFilterIconClick(type: TypeSimple) {
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

    fun onPokemonSortClick(sort: Sort) {
        selectedSort.value = sort
        val currentList = currentPokemonList.getAsSuccessState<List<PokemonSimple>>()?.data
        if (currentList != null)
            currentPokemonList.value = State.Success(pokemonListUseCase.sort(currentList, sort))
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