package com.houlis.haris.feature.details.ui

import com.houlis.haris.picfind.feature.details.ui.DetailsMiddleware
import com.houlis.haris.picfind.feature.details.ui.DetailsReducer
import com.houlis.haris.picfind.feature.details.ui.DetailsState
import com.houlis.haris.picfind.feature.details.ui.DetailsViewModel
import com.houlis.haris.picfind.feature.details.ui.LoadState
import com.houlis.haris.picfind.test.data.FakePicturesRepository
import com.houlis.haris.picfind.test.domain.provider.dummyPicture1
import com.houlis.haris.picfind.ui.common.testutil.TestCloseableScope
import com.houlis.haris.picfind.ui.common.testutil.assertStatesFor
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
