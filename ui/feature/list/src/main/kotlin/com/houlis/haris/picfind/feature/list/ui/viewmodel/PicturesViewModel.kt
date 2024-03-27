package com.houlis.haris.picfind.feature.list.ui.viewmodel

import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.picfind.core.data.SaveState
import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.OnPictureClicked
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.picfind.ui.common.mvi.MviViewModel
import com.houlis.haris.picfind.ui.common.mvi.MwProvider
import com.houlis.haris.picfind.ui.common.navigation.PicIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PicturesViewModel @Inject constructor(
    @PicIdArg private val picIdArg: String,
    private val saveState: SaveState<String>,
    scope: CloseableCoroutineScope,
    reducer: PicturesReducer,
    mwProvider: MwProvider<PicturesState, PicturesAction>,
) : MviViewModel<PicturesState, PicturesAction>(scope, reducer, mwProvider, PicturesState()) {

    fun searchFor(query: String) {
        dispatch(SearchFor(query))
    }

    fun onPictureClicked(picture: Picture) {
        saveState(picIdArg, picture.id)
        dispatch(OnPictureClicked(picture))
    }
}
