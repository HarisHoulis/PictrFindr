package com.houlis.haris.feature.details.ui

import com.houlis.haris.feature.details.ui.PicDetailsAction.DetailsFetched
import com.houlis.haris.feature.details.ui.PicDetailsAction.FetchDetailsFor
import com.houlis.haris.pictrfindr.ui.common.mvi.Reducer
import javax.inject.Inject

internal class DetailsReducer @Inject constructor() : Reducer<DetailsState, PicDetailsAction> {
    override fun reduce(state: DetailsState, action: PicDetailsAction): DetailsState =
        when (action) {
            is DetailsFetched -> state.copy(loadState = LoadState.Loaded, pic = action.image)
            is FetchDetailsFor -> state.copy(loadState = LoadState.Loading)
        }
}
