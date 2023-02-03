package com.houlis.haris.list.ui

import com.houlis.haris.list.domain.Picture

sealed interface PicturesUiState {
    data class Fetched(val pictures: List<Picture>) : PicturesUiState

    object Loading : PicturesUiState
    object Empty : PicturesUiState
    object Error : PicturesUiState
}
