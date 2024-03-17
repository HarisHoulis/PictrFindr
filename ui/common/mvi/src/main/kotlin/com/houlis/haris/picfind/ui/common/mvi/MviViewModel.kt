package com.houlis.haris.picfind.ui.common.mvi

import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.ViewModel
import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val BufferSize = 64

/**
 * Responsible for providing a list of [Middleware]s
 *
 * @param S - The [State] managed by this [ViewModel]
 * @param A - the [Action]s this [ViewModel] handles
 */
fun interface MwProvider<S : State, A : Action> {
    operator fun invoke(dispatcher: Dispatcher<A>): List<Middleware<S, A>>
}

/**
 * Base class for all [ViewModel]s adhering to the MVI framework
 *
 * @param S - The [State] managed by this [ViewModel]
 * @param A - the [Action]s this [ViewModel] handles
 * @param closeableScope - a [CloseableCoroutineScope] to launch coroutines on
 * @param reducer - the [Reducer] that generates new state from the current [State] and [Action]s
 * @param middlewaresProvider - provides a list of [Middleware]s to handle [Action]s
 * @param initialState - the initial [State]
 */
open class MviViewModel<S : State, A : Action>(
    private val closeableScope: CloseableCoroutineScope,
    private val reducer: Reducer<S, A>,
    private val middlewaresProvider: MwProvider<S, A> = MwProvider { emptyList() },
    initialState: S,
) : ViewModel(closeableScope), Dispatcher<A> {

    private val middlewares: List<Middleware<S, A>>
        get() = middlewaresProvider(this)

    private val actions = MutableSharedFlow<A>(extraBufferCapacity = BufferSize)

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)

    /** The [State] managed by this [MviViewModel] */
    var state: StateFlow<S> = _state.asStateFlow()

    init {
        closeableScope.launch {
            actions
                .onEach { action ->
                    middlewares.fastForEach { middleware ->
                        middleware.process(state.value, action)
                    }
                }
                .collect()
        }
        closeableScope.launch {
            actions.collect { action ->
                _state.update { currentState ->
                    reducer.reduce(currentState, action)
                }
            }
        }
    }

    override fun dispatch(action: A) {
        closeableScope.launch {
            actions.emit(action)
        }
    }

    override fun onCleared() {
        super.onCleared()
        middlewares.fastForEach { middleware -> middleware.onCleared() }
    }
}
