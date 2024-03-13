package com.houlis.haris.feature.list.ui

import app.cash.turbine.test
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.PicturesLoaded
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.feature.list.ui.viewmodel.PicturesMiddleware
import com.houlis.haris.feature.list.ui.viewmodel.PicturesState
import com.houlis.haris.pictrfindr.ui.common.testutil.TestCloseableScope
import com.houlis.haris.pictrfindr.ui.common.testutil.TestDispatcher
import com.houlis.haris.test.data.FakePicturesRepository
import com.houlis.haris.test.data.FakePicturesRepository.Query.Query1
import com.houlis.haris.test.data.FakePicturesRepository.Query.Query2
import com.houlis.haris.test.domain.provider.dummyPicture3
import com.houlis.haris.test.domain.provider.dummyPicture4
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.Duration

internal class PicturesMiddlewareTest {

    private val coroutineScope = TestScope(UnconfinedTestDispatcher())
    private val closeableScope = TestCloseableScope(coroutineScope.backgroundScope)

    private val testDispatcher = TestDispatcher<PicturesAction>()

    private fun sut(debounce: Duration = Duration.ofMillis(100)) = PicturesMiddleware(
        FakePicturesRepository(),
        debounce,
        testDispatcher,
        closeableScope
    )

    @Test
    fun `debounces multiple queries`() = coroutineScope.runTest {
        val expectedPictures = persistentListOf(dummyPicture3(), dummyPicture4())
        val sut = sut()

        sut.process(PicturesState(), SearchFor(Query1.text))
        sut.process(PicturesState(), SearchFor(Query2.text))

        testDispatcher.actionFlow.test {
            expectThat(awaitItem()).isEqualTo(PicturesLoaded(expectedPictures))
        }
    }
}
