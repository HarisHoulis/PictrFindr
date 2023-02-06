package com.houlis.haris.feature.details.ui

import app.cash.turbine.test
import com.houlis.haris.test.data.TestPicturesRepository
import com.houlis.haris.test.domain.provider.dummyPicture1
import com.houlis.haris.test.util.runTestWithDispatcher
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DetailsViewModelTest {

    private fun testedClass() = DetailsViewModel(TestPicturesRepository())

    @Test
    fun `displays loading`() = runTestWithDispatcher {
        testedClass().uiState.test {
            awaitItem() shouldBe DetailsUiState.Loading
        }
    }

    @Test
    fun `fetches picture`() = runTestWithDispatcher {
        val testedClass = testedClass()

        testedClass.uiState.test {
            testedClass.getImageFor("any")

            awaitItem() shouldBe DetailsUiState.Loading
            awaitItem() shouldBe DetailsUiState.Fetched(dummyPicture1().image)
        }
    }
}
