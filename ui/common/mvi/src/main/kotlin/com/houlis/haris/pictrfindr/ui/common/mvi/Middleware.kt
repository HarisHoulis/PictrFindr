package com.houlis.haris.pictrfindr.ui.common.mvi

import timber.log.Timber
import java.io.Closeable
import java.io.IOException

/**
 * Handles asynchronous [Action] processing in the MVI framework
 *
 * @param closeables A vararg of [Closeable] objects that will be closed when this [Middleware] is destroyed.
 */
abstract class Middleware<S : State, A : Action>(
    private val dispatcher: Dispatcher<A>,
    vararg closeables: Closeable,
) {
    private val closeables: Set<Closeable> = setOf(*closeables)

    /**
     * Processes the [Action].
     *
     * @param state the current [State]
     * @param action the [Action] to process
     */
    abstract suspend fun process(state: S, action: A)

    protected fun dispatch(action: A) = dispatcher.dispatch(action)

    internal fun onCleared() {
        closeables.forEach { closeable ->
            try {
                closeable.close()
            } catch (ex: IOException) {
                Timber.e(ex, "Exception closing closeable: $closeable")
            }
        }
    }
}
