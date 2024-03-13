package com.houlis.haris.feature.details.ui

import app.cash.turbine.test
import com.houlis.haris.test.data.FakePicturesRepository
import com.houlis.haris.test.domain.provider.dummyPicture1
import com.houlis.haris.test.util.runTestWithDispatcher
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DetailsViewModelTest {

    private fun testedClass() = DetailsViewModel(FakePicturesRepository())

    @Test
    fun `displays loading`() = runTestWithDispatcher {
        testedClass().uiState.test {
            expectThat(awaitItem()).isEqualTo(DetailsUiState.Loading)
        }
    }

    @Test
    fun `fetches picture`() = runTestWithDispatcher {
        val testedClass = testedClass()

        testedClass.uiState.test {
            testedClass.getImageFor("any")

            expectThat(awaitItem()).isEqualTo(DetailsUiState.Loading)
            expectThat(awaitItem()).isEqualTo(DetailsUiState.Fetched(dummyPicture1().image))
        }
    }
}
