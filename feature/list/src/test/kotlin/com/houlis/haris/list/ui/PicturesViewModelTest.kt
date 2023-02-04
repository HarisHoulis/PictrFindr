package com.houlis.haris.list.ui

import app.cash.turbine.test
import com.houlis.haris.list.data.PicturesRepository
import com.houlis.haris.list.domain.IMAGE_BASE_URL
import com.houlis.haris.list.domain.dummyPicture3
import com.houlis.haris.list.domain.dummyPicture4
import com.houlis.haris.list.domain.dummyPictures
import com.houlis.haris.list.ui.PicturesUiState.Type
import com.houlis.haris.list.ui.PicturesUiState.Type.Fetched
import com.houlis.haris.list.ui.PicturesUiState.Type.Loading
import com.houlis.haris.network.data.PicturesApi
import com.houlis.haris.test.data.TestPicturesApi
import com.houlis.haris.test.data.TestPicturesApi.Query.Query1
import com.houlis.haris.test.data.TestPicturesApi.Query.Query2
import com.houlis.haris.test.extension.MainTestDispatcherExtension
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Duration

@ExtendWith(MainTestDispatcherExtension::class)
internal class PicturesViewModelTest {

    companion object {
        @JvmStatic
        fun `api to expected UI state`() = listOf(
            Arguments.of(testPicturesApi(), Fetched(dummyPictures())),
            Arguments.of(testPicturesApi().apply { setEmptyResponse() }, Type.Empty),
            Arguments.of(testPicturesApi().apply { throwException() }, Type.Error),
        )

        private fun testPicturesApi() = TestPicturesApi()
    }

    private fun testedClass(api: PicturesApi = testPicturesApi(), debounce: Duration = Duration.ofMillis(0)) =
        PicturesViewModel(
            PicturesRepository(api, IMAGE_BASE_URL),
            debounce
        )

    @Test
    fun `initial state is loading`() = runTest {
        testedClass().state.test {
            awaitItem() shouldBe PicturesUiState("", Loading)
            expectNoEvents()
        }
    }

    @ParameterizedTest
    @MethodSource("api to expected UI state")
    fun `reduces state`(api: PicturesApi, expectedUiStateType: Type) = runTest {
        // ARRANGE
        val initialState = PicturesUiState()
        val testedClass = testedClass(api)

        // ACT
        testedClass.state.test {
            testedClass.searchFor(Query1.text)

            // ASSERT
            awaitItem() shouldBe initialState
            awaitItem() shouldBe initialState.copy(input = Query1.text)
            awaitItem() shouldBe initialState.copy(Query1.text, expectedUiStateType)
            expectNoEvents()
        }
    }

    @Test
    fun `reduces state based on most recent query`() = runTest {
        // ARRANGE
        val initialState = PicturesUiState("", Loading)
        val testedClass = testedClass(debounce = Duration.ofMillis(10))

        // ACT
        testedClass.state.test {
            testedClass.searchFor(Query1.text)
            testedClass.searchFor(Query2.text)

            // ASSERT
            awaitItem() shouldBe initialState
            awaitItem() shouldBe initialState.copy(input = Query1.text)
            awaitItem() shouldBe initialState.copy(input = Query2.text)
            awaitItem() shouldBe initialState.copy(Query2.text, type = Fetched(listOf(dummyPicture3(), dummyPicture4())))
            expectNoEvents()
        }
    }
}
