package com.houlis.haris.feature.list.ui

import com.houlis.haris.core.domain.Picture

internal data class PicturesUiState(val input: String, val type: Type, val navigateToDetails: NavigateToDetails? = null) {

    companion object {
        operator fun invoke() = PicturesUiState("", Type.Initial)
    }

    sealed interface Type {
        data class Fetched(val pictures: List<Picture>) : Type

        object Initial : Type
        object Loading : Type
        object Empty : Type
        object Error : Type
    }

    data class NavigateToDetails(val picId: String)
}
