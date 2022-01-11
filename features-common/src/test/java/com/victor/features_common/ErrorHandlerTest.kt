package com.victor.features_common

import com.victor.networking.PokedexException
import junit.framework.Assert.assertEquals
import org.junit.Test

class ErrorHandlerTest {

    @Test
    fun connectionException_returnsConnectionString() {
        val errorString = ErrorHandler.handleMessage(PokedexException.ConnectionException)
        assertEquals(R.string.error_connection_exception, errorString)
    }

    @Test
    fun formatException_returnsFormatString() {
        val errorString = ErrorHandler.handleMessage(PokedexException.FormatException)
        assertEquals(R.string.error_format_exception, errorString)
    }

    @Test
    fun unexpectedException_returnsUnexpectedString() {
        val errorString = ErrorHandler.handleMessage(PokedexException.UnexpectedException)
        assertEquals(R.string.error_unknown_exception, errorString)
    }
}