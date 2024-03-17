package com.houlis.haris.picfind.ui.common.testutil

import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlin.coroutines.CoroutineContext

/**
 * A class that implements the [CloseableCoroutineScope] interface to provide a closeable coroutine scope for tests.
 */
class TestCloseableScope(private val scope: CoroutineScope = TestScope(UnconfinedTestDispatcher())) :
    CloseableCoroutineScope {

    /**
     * Returns the [coroutineContext] for the passed [scope]
     */
    override val coroutineContext: CoroutineContext
        get() = scope.coroutineContext

    /** @{inheritDoc} */
    override fun close() = coroutineContext.cancel()
}
