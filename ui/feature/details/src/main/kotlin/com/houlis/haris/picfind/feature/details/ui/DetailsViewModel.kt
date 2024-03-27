package com.houlis.haris.picfind.feature.details.ui

import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.picfind.core.data.ReadState
import com.houlis.haris.picfind.feature.details.navigation.PicDetailsArg
import com.houlis.haris.picfind.feature.details.ui.PicDetailsAction.FetchDetailsFor
import com.houlis.haris.picfind.ui.common.mvi.Dispatcher
import com.houlis.haris.picfind.ui.common.mvi.MviViewModel
import com.houlis.haris.picfind.ui.common.mvi.MwProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    readState: ReadState<String>,
    scope: CloseableCoroutineScope,
    reducer: DetailsReducer,
    mwProvider: MwProvider<DetailsState, PicDetailsAction>,
) : MviViewModel<DetailsState, PicDetailsAction>(scope, reducer, mwProvider, DetailsState()),
    Dispatcher<PicDetailsAction> {

    private val picId = PicDetailsArg(readState).picId

    init {
        dispatch(FetchDetailsFor(picId))
    }

}
