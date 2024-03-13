package com.houlis.haris.feature.list.ui.viewmodel

import com.houlis.haris.core.domain.Picture
import com.houlis.haris.pictrfindr.ui.common.mvi.Action
import kotlinx.collections.immutable.ImmutableList

internal sealed interface PicturesAction : Action {
    data class SearchFor(val query: String) : PicturesAction
    data class NavigateToPictureDetails(val picture: Picture) : PicturesAction
    data class PicturesLoaded(val pictures: ImmutableList<Picture>) : PicturesAction
    data object NoResults : PicturesAction
    data object Error : PicturesAction
}
