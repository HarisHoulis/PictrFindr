package com.houlis.haris.picfind.test.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

/**
 * Substitutes main [CoroutineDispatcher] with [testDispatcher].
 *
 * This is handy when testing hot flows (mainly ViewModels), since their emissions happen inside launch blocks and need to
 * wait for all of their emissions.
 *
 * See [Test API reference](https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test)
 * and [Turbine GH issue](https://github.com/Kotlin/kotlinx.coroutines/issues/3339)
 */
fun runTestWithDispatcher(
    testDispatcher: TestDispatcher = StandardTestDispatcher(),
    test: suspend TestScope.() -> Unit,
) {
    Dispatchers.setMain(testDispatcher)

    return runTest {
        test()
        Dispatchers.resetMain()
    }
}
