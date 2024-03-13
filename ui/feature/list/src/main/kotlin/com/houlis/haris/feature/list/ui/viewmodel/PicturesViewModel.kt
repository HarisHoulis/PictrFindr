package com.houlis.haris.feature.list.ui.viewmodel

import com.houlis.haris.core.domain.Picture
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.NavigateToPictureDetails
import com.houlis.haris.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.pictrfindr.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.pictrfindr.ui.common.mvi.MviViewModel
import com.houlis.haris.pictrfindr.ui.common.mvi.MwProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PicturesViewModel @Inject constructor(
    scope: CloseableCoroutineScope,
    reducer: PicturesReducer,
    mwProvider: MwProvider<PicturesState, PicturesAction>,
) : MviViewModel<PicturesState, PicturesAction>(scope, reducer, mwProvider, PicturesState()) {

    fun searchFor(query: String) {
        dispatch(SearchFor(query))
    }

    fun onPictureClicked(picture: Picture) {
        dispatch(NavigateToPictureDetails(picture))
    }
}
