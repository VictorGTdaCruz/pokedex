package com.victor.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
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
        is TimeoutException,
        is SocketException -> PokedexException.ConnectionException
        else -> PokedexException.UnexpectedException
    }