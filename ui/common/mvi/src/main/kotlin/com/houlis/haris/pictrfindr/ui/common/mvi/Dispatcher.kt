package com.houlis.haris.pictrfindr.ui.common.mvi

/** Dispatches [Action]s to be handled by the MVI framework */
fun interface Dispatcher<A : Action> {

    /**
     * Dispatches an [action] to be processed by the MVI framework
     */
    fun dispatch(action: A)
}
