package com.victor.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeoutException

suspend fun <T> request(
    action: suspend () -> T
): T =
    supervisorScope {
        withContext(Dispatchers.IO) {
            runCatching { action() }
                .getOrElse { error ->
                    throw error.toPokedexException()
                }
        }
    }

private fun Throwable.toPokedexException(): PokedexException =
    when (this) {
        is IOException,
        is HttpException,
        is TimeoutException -> PokedexException.ConnectionException()
        is IllegalArgumentException -> PokedexException.FormatException()
        else -> PokedexException.UnexpectedException()
    }