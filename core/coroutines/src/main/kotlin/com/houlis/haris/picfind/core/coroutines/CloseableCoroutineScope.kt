package com.houlis.haris.picfind.core.coroutines

import com.houlis.haris.picfind.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * A [CoroutineScope] that implements the [Closeable] interface so that it can be used in ViewModels or other contexts
 * where a [Closeable] is used to cancel the [CoroutineScope].
 *
 * This [CoroutineScope] uses a [SupervisorJob] so that children cancellations and exceptions do not propagate to other
 * children coroutines.
 */
interface CloseableCoroutineScope : Closeable, CoroutineScope

internal class ProdCloseableCoroutineScope @Inject constructor(dispatcherProvider: DispatcherProvider) :
    CloseableCoroutineScope {
    /** @{inheritDoc} */
    override val coroutineContext: CoroutineContext = SupervisorJob() + dispatcherProvider.mainImmediate

    /** @{inheritDoc} */
    override fun close() = coroutineContext.cancel()
}
