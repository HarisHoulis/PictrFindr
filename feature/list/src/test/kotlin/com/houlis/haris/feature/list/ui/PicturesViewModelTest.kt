package com.houlis.haris.feature.list.ui

import app.cash.turbine.test
import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.feature.list.ui.PicturesUiState.Type
import com.houlis.haris.feature.list.ui.PicturesUiState.Type.Fetched
import com.houlis.haris.feature.list.ui.PicturesUiState.Type.Initial
import com.houlis.haris.feature.list.ui.PicturesUiState.Type.Loading
import com.houlis.haris.test.data.TestPicturesRepository
import com.houlis.haris.test.data.TestPicturesRepository.Query.Query1
import com.houlis.haris.test.data.TestPicturesRepository.Query.Query2
import com.houlis.haris.test.domain.provider.dummyPicture1
import com.houlis.haris.test.domain.provider.dummyPicture3
import com.houlis.haris.test.domain.provider.dummyPicture4
import com.houlis.haris.test.domain.provider.dummyPictures
import com.houlis.haris.test.util.runTestWithDispatcher
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Duration

internal class PicturesViewModelTest {

    companion object {
        @JvmStatic
        fun `repo to expected UI state`() = listOf(
            Arguments.of(testPicturesRepo(), Fetched(dummyPictures())),
            Arguments.of(testPicturesRepo().apply { setEmptyResponse() }, Type.Empty),
            Arguments.of(testPicturesRepo().apply { throwException() }, Type.Error)
        )

        private fun testPicturesRepo() = TestPicturesRepository()
    }

    private fun testedClass(
        repo: PicturesRepositoryContract = testPicturesRepo(),
        debounce: Duration = Duration.ofMillis(0),
    ) = PicturesViewModel(repo, debounce)

    @Test
    fun `has initial state`() = runTestWithDispatcher {
        testedClass().state.test {
            awaitItem() shouldBe PicturesUiState("", Initial)
            expectNoEvents()
        }
    }

    @ParameterizedTest
    @MethodSource("repo to expected UI state")
    fun `reduces state`(repo: PicturesRepositoryContract, expectedUiStateType: Type) =
        runTestWithDispatcher {
            // ARRANGE
            val initialState = PicturesUiState()
            val testedClass by lazy { testedClass(repo = repo) }

            // ACT
            testedClass.state.test {
                testedClass.searchFor(Query1.text)

                // ASSERT
                awaitItem() shouldBe initialState
                awaitItem() shouldBe initialState.copy(Query1.text, Loading)
                awaitItem() shouldBe initialState.copy(Query1.text, expectedUiStateType)
                expectNoEvents()
            }
        }

    @Test
    fun `reduces state based on most recent query`() = runTestWithDispatcher {
        // ARRANGE
        val initialState = PicturesUiState("", Initial)
        val testedClass = testedClass(debounce = Duration.ofMillis(10))

        // ACT
        testedClass.state.test {
            testedClass.searchFor(Query1.text)
            testedClass.searchFor(Query2.text)

            // ASSERT
            awaitItem() shouldBe initialState
            awaitItem() shouldBe initialState.copy(input = Query1.text, Loading)
            awaitItem() shouldBe initialState.copy(input = Query2.text, Loading)
            awaitItem() shouldBe initialState.copy(
                Query2.text,
                type = Fetched(
                    listOf(
                        dummyPicture3(),
                        dummyPicture4()
                    )
                )
            )
            expectNoEvents()
        }
    }

    @Test
    fun `navigates to details screen`() = runTestWithDispatcher(UnconfinedTestDispatcher()) {
        // ARRANGE
        val initialState = PicturesUiState("", Initial)
        val testedClass = testedClass(debounce = Duration.ofMillis(10))

        // ACT
        testedClass.state.test {
            testedClass.onPictureClicked(dummyPicture1())
            testedClass.onNavigatedToDetails()

            // ASSERT
            awaitItem() shouldBe initialState
            awaitItem() shouldBe initialState.copy(navigateToDetails = PicturesUiState.NavigateToDetails("52663187230"))
            awaitItem() shouldBe initialState.copy(navigateToDetails = null)
        }
    }
}
