package com.houlis.haris.feature.details.ui

import com.houlis.haris.pictrfindr.ui.common.testutil.TestCloseableScope
import com.houlis.haris.pictrfindr.ui.common.testutil.assertStatesFor
import com.houlis.haris.test.data.FakePicturesRepository
import com.houlis.haris.test.domain.provider.dummyPicture1
import org.junit.jupiter.api.Test

internal class DetailsViewModelTest {

    private val scope = TestCloseableScope()
    private val sut = DetailsViewModel(
        scope,
        DetailsReducer()
    ) { dispatcher ->
        listOf(DetailsMiddleware(FakePicturesRepository(), dispatcher, scope))
    }

    @Test
    fun `fetches picture's details`() {
        with(scope) {
            sut.assertStatesFor(
                DetailsState(),
                DetailsState(LoadState.Loading, null),
                DetailsState(LoadState.Loaded, dummyPicture1().image)
            ) {
                detailsFor("any")
            }
        }
    }
}
