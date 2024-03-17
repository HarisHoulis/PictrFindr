package com.houlis.haris.picfind.feature.details.ui

import com.houlis.haris.picfind.core.domain.Picture.Image
import com.houlis.haris.picfind.ui.common.mvi.Action

internal sealed interface PicDetailsAction : Action {
    data class FetchDetailsFor(val imageId: String) : PicDetailsAction
    data class DetailsFetched(val image: Image) : PicDetailsAction
}
