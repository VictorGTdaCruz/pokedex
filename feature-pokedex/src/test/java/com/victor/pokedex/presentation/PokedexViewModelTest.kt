package com.victor.pokedex.presentation

import com.victor.features_common.MainCoroutineRuleTest
import com.victor.features_common.State
import com.victor.features_common.getAsSuccessResource
import com.victor.pokedex.domain.model.PokemonDetails
import com.victor.pokedex.domain.model.PokemonType
import com.victor.pokedex.domain.model.TypeDetails
import com.victor.pokedex.domain.service.PokedexService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokedexViewModelTest {

    private lateinit var viewModel: PokedexViewModel
    private lateinit var service: PokedexService

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRuleTest()

    @Before
    fun before() {
        service = mockk()
        viewModel = PokedexViewModel(service)
    }

    @Test
    fun whenLoadPokemonTypes_callsInfrastructureGetPokemonTypes() {
        viewModel.loadPokemonTypes()
        coVerify { service.getPokemonTypes() }
    }

    @Test
    fun whenLoadPokemonTypes_storesResultInState() {
        coEvery { service.getPokemonTypes() } returns listOf(PokemonType(0L, "test"))
        viewModel.loadPokemonTypes()
        assertEquals(1, viewModel.pokemonTypes.getAsSuccessResource<List<PokemonType>>()?.data?.size)
    }

    @Test
    fun whenLoadPokemonTypes_notLoadIfIsHasContent() {
        viewModel.pokemonTypes.value = State.Success(listOf("test"))
        viewModel.loadPokemonTypes()
        coVerify(exactly = 0) { service.getPokemonTypes() }
    }

    @Test
    fun whenLoadPokemonsFromTypes_callsInfrastructureGetTypeDetails() {
        val type = 0L
        viewModel.loadPokemonsFromType(type)
        coVerify { service.getTypeDetails(type) }
    }

    @Test
    fun whenLoadPokemonsFromTypes_notLoadIfIsHasContentOfSameId() {
        val typeId = 0L
        val details = TypeDetails(id = typeId, name = "", pokemons = emptyList())
        viewModel.typeDetails.value = State.Success(details)
        viewModel.loadPokemonsFromType(typeId)
        coVerify(exactly = 0) { service.getTypeDetails(typeId) }
    }

    @Test
    fun whenLoadPokemonsFromTypes_shouldLoadIfIsHasContentOfDifferentId() {
        val typeId = 0L
        val details = TypeDetails(id = 1, name = "", pokemons = emptyList())
        viewModel.typeDetails.value = State.Success(details)
        viewModel.loadPokemonsFromType(typeId)
        coVerify(exactly = 1) { service.getTypeDetails(typeId) }
    }

    @Test
    fun whenLoadPokemonDetails_callsInfrastructureGetPokemonDetails() {
        val id = 0L
        viewModel.loadPokemonDetails(id)
        coVerify { service.getPokemonDetails(id) }
    }

    @Test
    fun whenLoadPokemonDetails_storesResultInState() {
        assertTrue(viewModel.pokemonDetails.isEmpty())
        val id = 0L
        val details = mockk<PokemonDetails>(relaxed = true)
        coEvery { service.getPokemonDetails(id) } returns details
        viewModel.loadPokemonDetails(id)
        assertEquals(details, viewModel.pokemonDetails[id])
        assertEquals(1, viewModel.pokemonDetails.size)
    }
}