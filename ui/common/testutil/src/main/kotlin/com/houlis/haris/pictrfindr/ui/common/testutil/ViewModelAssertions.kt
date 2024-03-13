package com.houlis.haris.pictrfindr.ui.common.testutil

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.houlis.haris.pictrfindr.ui.common.mvi.Action
import com.houlis.haris.pictrfindr.ui.common.mvi.MviViewModel
import com.houlis.haris.pictrfindr.ui.common.mvi.State
import kotlinx.coroutines.test.runTest
import strikt.api.expectThat
import strikt.assertions.isEqualTo

fun <S : State, A : Action, VM : MviViewModel<S, A>> VM.assertStatesFor(
    vararg expectedStates: S,
    f: VM.() -> Unit,
) {
    runTest {
        state.test {
            f()

            check(*expectedStates)
        }
    }
}

suspend fun <S : State> TurbineTestContext<S>.check(vararg expectedStates: S) {
    expectedStates.forEach { expectedState ->
        expectThat(awaitItem()).isEqualTo(expectedState)
    }
}
