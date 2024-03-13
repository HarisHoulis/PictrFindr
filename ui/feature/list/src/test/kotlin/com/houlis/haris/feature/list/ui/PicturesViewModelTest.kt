package com.houlis.haris.feature.list.ui

import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.feature.list.ui.viewmodel.LoadState.Error
import com.houlis.haris.feature.list.ui.viewmodel.LoadState.Loaded
import com.houlis.haris.feature.list.ui.viewmodel.LoadState.Loading
import com.houlis.haris.feature.list.ui.viewmodel.LoadState.NoResults
import com.houlis.haris.feature.list.ui.viewmodel.PicturesMiddleware
import com.houlis.haris.feature.list.ui.viewmodel.PicturesReducer
import com.houlis.haris.feature.list.ui.viewmodel.PicturesState
import com.houlis.haris.feature.list.ui.viewmodel.PicturesViewModel
import com.houlis.haris.pictrfindr.ui.common.testutil.TestCloseableScope
import com.houlis.haris.pictrfindr.ui.common.testutil.assertStatesFor
import com.houlis.haris.test.data.FakePicturesRepository
import com.houlis.haris.test.data.FakePicturesRepository.Query.Query1
import com.houlis.haris.test.domain.provider.dummyPicture1
import com.houlis.haris.test.domain.provider.dummyPicture2
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test
import java.time.Duration

internal class PicturesViewModelTest {

    private val coroutineScope = TestScope(UnconfinedTestDispatcher())
    private val scope = TestCloseableScope(coroutineScope.backgroundScope)

    private fun picturesRepository() = FakePicturesRepository()

    private fun sut(repo: PicturesRepositoryContract = picturesRepository()) = PicturesViewModel(
        scope,
        PicturesReducer()
    ) { dispatcher ->
        listOf(
            PicturesMiddleware(repo, Duration.ofMillis(0), dispatcher, scope)
        )
    }

    @Test
    fun `loads pictures`() = with(coroutineScope) {
        sut().assertStatesFor(PicturesState(), expectedLoadingState, expectedLoadedState) {
            searchFor(Query1.text)
        }
    }

    @Test
    fun `reports error when failing to load pictures`() = with(coroutineScope) {
        sut(picturesRepository().apply { throwException() }).assertStatesFor(
            PicturesState(),
            expectedLoadingState,
            expectedErrorState
        ) {
            searchFor(Query1.text)
        }
    }

    @Test
    fun `reports no results`() = with(coroutineScope) {
        sut(picturesRepository().apply { setEmptyResponse() }).assertStatesFor(
            PicturesState(),
            expectedLoadingState,
            expectedNoResultsState
        ) {
            searchFor(Query1.text)
        }
    }
}

private val expectedLoadingState = PicturesState(
    Loading,
    persistentListOf(),
    null
)

private val expectedLoadedState = PicturesState(
    Loaded,
    persistentListOf(dummyPicture1(), dummyPicture2()),
    null
)

private val expectedErrorState = PicturesState(
    Error,
    persistentListOf(),
    null
)

private val expectedNoResultsState = PicturesState(
    NoResults,
    persistentListOf(),
    null
)
