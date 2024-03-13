package com.houlis.haris.feature.details.ui

import com.houlis.haris.core.domain.Picture.Image
import com.houlis.haris.pictrfindr.ui.common.mvi.Action

internal sealed interface PicDetailsAction : Action {
    data class FetchDetailsFor(val imageId: String) : PicDetailsAction
    data class DetailsFetched(val image: Image) : PicDetailsAction
}
