package com.houlis.haris.picfind.feature.list.ui.viewmodel

import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.Error
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.NoResults
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.OnPictureClicked
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.PictureSaved
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.PicturesLoaded
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.picfind.ui.common.mvi.Reducer
import javax.inject.Inject

internal class PicturesReducer @Inject constructor() : Reducer<PicturesState, PicturesAction> {
    override fun reduce(state: PicturesState, action: PicturesAction): PicturesState = when (action) {
        is PictureSaved -> state.copy(navigateToDetails = action.picture)
        is PicturesLoaded -> state.copy(
            loadState = LoadState.Loaded,
            pictures = action.pictures
        )

        is SearchFor -> state.copy(loadState = LoadState.Loading)
        Error -> state.copy(loadState = LoadState.Error)
        NoResults -> state.copy(loadState = LoadState.NoResults)
        is OnPictureClicked -> state
    }
}
