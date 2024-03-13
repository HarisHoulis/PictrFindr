package com.houlis.haris.feature.list.ui.viewmodel

import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.Error
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.NavigateToPictureDetails
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.NoResults
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.PicturesLoaded
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.pictrfindr.ui.common.mvi.Reducer
import javax.inject.Inject

internal class PicturesReducer @Inject constructor() : Reducer<PicturesState, PicturesAction> {
    override fun reduce(state: PicturesState, action: PicturesAction): PicturesState = when (action) {
        is NavigateToPictureDetails -> state.copy(navigateToDetails = action.picture)
        is PicturesLoaded -> state.copy(
            loadState = LoadState.Loaded,
            pictures = action.pictures
        )

        is SearchFor -> state.copy(loadState = LoadState.Loading)
        Error -> state.copy(loadState = LoadState.Error)
        NoResults -> state.copy(loadState = LoadState.NoResults)
    }
}
