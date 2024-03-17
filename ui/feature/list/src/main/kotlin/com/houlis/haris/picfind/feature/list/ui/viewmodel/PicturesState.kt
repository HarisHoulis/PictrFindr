package com.houlis.haris.picfind.feature.list.ui.viewmodel

import androidx.compose.runtime.Immutable
import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState.Idle
import com.houlis.haris.picfind.ui.common.mvi.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal enum class LoadState {
    Idle, Loading, Loaded, NoResults, Error,
}

@Immutable
internal data class PicturesState(
    val loadState: LoadState,
    val pictures: ImmutableList<Picture>,
    val navigateToDetails: Picture?,
) : State {

    companion object {
        operator fun invoke() = PicturesState(Idle, persistentListOf(), null)
    }
}
