package com.victor.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException

suspend fun <T> request(
    action: suspend () -> T
): T =
    supervisorScope {
        withContext(Dispatchers.IO) {
            runCatching { action() }
                .getOrElse { error ->
                    // mapeia uma exception
                    throw error
                }
        }
    }

private fun Throwable.toInfrastructureError() {
    when (this) {
        is HttpException -> {}
    }
}