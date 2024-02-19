package com.victor.features_common

import androidx.compose.runtime.mutableStateOf
import com.victor.networking.PokedexException.UnexpectedException
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class StateTest {

    @Test
    fun shouldGetResourceAsSuccess() {
        val successState = State.Success(data = "")
        val mutableState = mutableStateOf<State>(successState)
        assertEquals(successState, mutableState.getAsSuccessState<String>())
    }

    @Test
    fun shouldFailToGetLoadingResourceAsSuccess() {
        val mutableState = mutableStateOf<State>(State.Loading)
        assertNull(mutableState.getAsSuccessState<String>())
    }

    @Test
    fun shouldFailToGetEmptyResourceAsSuccess() {
        val mutableState = mutableStateOf<State>(State.Empty)
        assertNull(mutableState.getAsSuccessState<String>())
    }

    @Test
    fun shouldFailToGetErrorResourceAsSuccess() {
        val mutableState = mutableStateOf<State>(State.Error(UnexpectedException))
        assertNull(mutableState.getAsSuccessState<String>())
    }

    @Test
    fun shouldGetResourceAsError() {
        val errorState = State.Error(UnexpectedException)
        val mutableState = mutableStateOf<State>(errorState)
        assertEquals(errorState, mutableState.getAsErrorState())
    }

    @Test
    fun shouldFailToGetSuccessResourceAsError() {
        val mutableState = mutableStateOf<State>(State.Success(""))
        assertNull(mutableState.getAsErrorState())
    }

    @Test
    fun shouldFailToGetLoadingResourceAsError() {
        val mutableState = mutableStateOf<State>(State.Loading)
        assertNull(mutableState.getAsErrorState())
    }

    @Test
    fun shouldFailToGetEmptyResourceAsError() {
        val mutableState = mutableStateOf<State>(State.Empty)
        assertNull(mutableState.getAsErrorState())
    }
}