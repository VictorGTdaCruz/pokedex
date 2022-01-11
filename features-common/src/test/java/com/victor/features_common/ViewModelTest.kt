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

class MockViewModel: ViewModel()

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
        val state = mutableStateOf<Resource>(Resource.Loading)
            viewModel.manageResourcesDuring(state) {
                emptyList<String>()
            }

        assertEquals(Resource.Empty, state.value)
    }

    @Test
    fun shouldManageResourcesToError() {
        val state = mutableStateOf<Resource>(Resource.Empty)
            viewModel.manageResourcesDuring(state) {
                throw PokedexException.UnexpectedException
            }

        assertTrue(state.value is Resource.Error)
    }

    @Test
    fun shouldManageResourcesToSuccess() {
        val state = mutableStateOf<Resource>(Resource.Empty)
            viewModel.manageResourcesDuring(state) {
                "data"
            }

        assertEquals("data", state.getAsSuccessResource<String>()?.data)
    }
}