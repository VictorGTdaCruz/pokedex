package com.victor.features_common

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.victor.networking.PokedexException
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MockViewModel : ViewModel()

class ViewModelTest {

    private lateinit var viewModel: ViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRuleTest()

    @Before
    fun before() {
        viewModel = MockViewModel()
    }

    @Test
    fun shouldManageResourcesToEmpty() {
        val state = mutableStateOf<State>(State.Loading)
        viewModel.manageStateDuringRequest(state) {
            emptyList<String>()
        }

        assertEquals(State.Empty, state.value)
    }

    @Test
    fun shouldManageResourcesToError() {
        val state = mutableStateOf<State>(State.Empty)
        viewModel.manageStateDuringRequest(state) {
            throw PokedexException.UnexpectedException
        }

        assertTrue(state.value is State.Error)
    }

    @Test
    fun shouldManageResourcesToSuccess() {
        val state = mutableStateOf<State>(State.Empty)
        viewModel.manageStateDuringRequest(state) {
            "data"
        }

        assertEquals("data", state.getAsSuccessResource<String>()?.data)
    }
}