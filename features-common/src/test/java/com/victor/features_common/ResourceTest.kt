package com.victor.features_common

import androidx.compose.runtime.mutableStateOf
import com.victor.networking.PokedexException.UnexpectedException
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test

class ResourceTest {

    @Test
    fun shouldGetResourceAsSuccess() {
        val successResource = Resource.Success(data = "")
        val mutableState = mutableStateOf<Resource>(successResource)
        assertEquals(successResource, mutableState.getAsSuccessResource<String>())
    }

    @Test
    fun shouldFailToGetLoadingResourceAsSuccess() {
        val mutableState = mutableStateOf<Resource>(Resource.Loading)
        assertNull(mutableState.getAsSuccessResource<String>())
    }

    @Test
    fun shouldFailToGetEmptyResourceAsSuccess() {
        val mutableState = mutableStateOf<Resource>(Resource.Empty)
        assertNull(mutableState.getAsSuccessResource<String>())
    }

    @Test
    fun shouldFailToGetErrorResourceAsSuccess() {
        val mutableState = mutableStateOf<Resource>(Resource.Error(UnexpectedException))
        assertNull(mutableState.getAsSuccessResource<String>())
    }

    @Test
    fun shouldGetResourceAsError() {
        val errorResource = Resource.Error(UnexpectedException)
        val mutableState = mutableStateOf<Resource>(errorResource)
        assertEquals(errorResource, mutableState.getAsErrorResource())
    }

    @Test
    fun shouldFailToGetSuccessResourceAsError() {
        val mutableState = mutableStateOf<Resource>(Resource.Success(""))
        assertNull(mutableState.getAsErrorResource())
    }

    @Test
    fun shouldFailToGetLoadingResourceAsError() {
        val mutableState = mutableStateOf<Resource>(Resource.Loading)
        assertNull(mutableState.getAsErrorResource())
    }

    @Test
    fun shouldFailToGetEmptyResourceAsError() {
        val mutableState = mutableStateOf<Resource>(Resource.Empty)
        assertNull(mutableState.getAsErrorResource())
    }
}