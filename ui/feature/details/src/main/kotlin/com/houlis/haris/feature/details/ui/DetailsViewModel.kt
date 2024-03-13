package com.houlis.haris.feature.details.ui

import com.houlis.haris.feature.details.ui.PicDetailsAction.FetchDetailsFor
import com.houlis.haris.pictrfindr.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.pictrfindr.ui.common.mvi.Dispatcher
import com.houlis.haris.pictrfindr.ui.common.mvi.MviViewModel
import com.houlis.haris.pictrfindr.ui.common.mvi.MwProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    scope: CloseableCoroutineScope,
    reducer: DetailsReducer,
    mwProvider: MwProvider<DetailsState, PicDetailsAction>,
) : MviViewModel<DetailsState, PicDetailsAction>(scope, reducer, mwProvider, DetailsState()),
    Dispatcher<PicDetailsAction> {

    fun detailsFor(picId: String) {
        dispatch(FetchDetailsFor(picId))
    }
}
