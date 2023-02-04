package com.houlis.haris.list.ui

import com.houlis.haris.list.domain.Picture

data class PicturesUiState(val input: String, val type: Type) {

    companion object {
        operator fun invoke() = PicturesUiState("", Type.Loading)
    }

    sealed interface Type {
        data class Fetched(val pictures: List<Picture>) : Type
        object Loading : Type
        object Empty : Type
        object Error : Type
    }
}
