package com.houlis.haris.picfind.ui.common.testutil

import com.houlis.haris.picfind.ui.common.mvi.Action
import com.houlis.haris.picfind.ui.common.mvi.Dispatcher
import kotlinx.coroutines.flow.MutableSharedFlow

private const val ACTION_FLOW_EXTRA_BUFFER_CAPACITY = 64

/**
 * An implementation of the [Dispatcher] interface for tests that allows dispatching actions of type [A].
 * This class uses a [MutableSharedFlow] to emit the dispatched actions, which can be observed.
 *
 * @param A The type of actions that can be dispatched.
 */
class TestDispatcher<A : Action> : Dispatcher<A> {

    /**
     * The [MutableSharedFlow] used to emit the dispatched actions to observers.
     */
    val actionFlow = MutableSharedFlow<A>(extraBufferCapacity = ACTION_FLOW_EXTRA_BUFFER_CAPACITY)

    /**
     * Dispatches an action of type [A] to any registered observers.
     *
     * @param action The action to be dispatched.
     */
    override fun dispatch(action: A) {
        actionFlow.tryEmit(action)
    }
}
