package com.houlis.haris.network

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> asResult(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T,
): Result4k<T, Exception> = suspendRunCatching {
    withContext(dispatcher) {
        apiCall()
    }
}

/**
 * Invokes [block], returning a [Success] if it succeeds, otherwise a [Failure]
 * taking care not to break structured concurrency.
 *
 * See [issue](https://github.com/Kotlin/kotlinx.coroutines/issues/1814).
 */
private suspend fun <T> suspendRunCatching(block: suspend () -> T): Result4k<T, Exception> = try {
    Success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Failure(exception)
}
