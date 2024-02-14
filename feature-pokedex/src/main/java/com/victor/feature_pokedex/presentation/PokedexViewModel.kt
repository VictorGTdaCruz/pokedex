package com.victor.feature_pokedex.presentation

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
    var pokemonTypes = mutableStateOf<State>(State.Empty)

    fun getPokemonList(typeList: List<PokemonType>? = null) {
        manageStateDuringRequest( // TODO fullPokemonList estiver preenchido nao precisa pegar de novo
            mutableState = currentPokemonList,
            request = { useCase.getPokemonList(typeList) },
            onSuccess = {
                if (typeList.isNullOrEmpty() && fullPokemonList.isEmpty())
                    fullPokemonList = it
            },
        )
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
}