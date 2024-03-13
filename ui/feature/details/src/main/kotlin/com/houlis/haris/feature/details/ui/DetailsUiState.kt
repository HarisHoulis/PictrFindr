package com.houlis.haris.feature.details.ui

import androidx.compose.runtime.Immutable
import com.houlis.haris.core.domain.Picture.Image
import com.houlis.haris.feature.details.ui.LoadState.Idle
import com.houlis.haris.pictrfindr.ui.common.mvi.State

internal enum class LoadState {
    Idle, Loading, Loaded, NoResults, Error,
}

@Immutable
internal data class DetailsState(
    val loadState: LoadState,
    val pic: Image?,
) : State {
    companion object {
        operator fun invoke() = DetailsState(
            loadState = Idle,
            pic = null
        )
    }
}
