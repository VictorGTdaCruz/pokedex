package com.victor.networking

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.SocketException
import java.util.concurrent.TimeoutException

class RequestTest {

    @Test
    fun shouldRunRequestSuccessfully() {
        val result = runBlocking {
            request { "test" }
        }
        assertEquals("test", result)
    }

    @Test
    fun shouldMapPokedexConnectionExceptionFromIOException() {
        try {
            runBlocking {
                request { throw IOException() }
            }
        } catch (exception: PokedexException.ConnectionException) { }
    }

    @Test
    fun shouldMapPokedexConnectionExceptionFromHttpException() {
        try {
            runBlocking {
                request { throw HttpException(Response.success(null)) }
            }
        } catch (exception: PokedexException.ConnectionException) { }
    }

    @Test
    fun shouldMapPokedexConnectionExceptionFromTimeoutException() {
        try {
            runBlocking {
                request { throw TimeoutException() }
            }
        } catch (exception: PokedexException.ConnectionException) { }
    }

    @Test
    fun shouldMapPokedexConnectionExceptionFromSocketException() {
        try {
            runBlocking {
                request { throw SocketException() }
            }
        } catch (exception: PokedexException.ConnectionException) { }
    }

    @Test
    fun shouldMapPokedexFormatException() {
        try {
            runBlocking {
                request { throw IllegalArgumentException() }
            }
        } catch (exception: PokedexException.FormatException) { }
    }

    @Test
    fun shouldMapPokedexUnexpectedException() {
        try {
            runBlocking {
                request { throw ClassNotFoundException() }
            }
        } catch (exception: PokedexException.UnexpectedException) { }
    }
}