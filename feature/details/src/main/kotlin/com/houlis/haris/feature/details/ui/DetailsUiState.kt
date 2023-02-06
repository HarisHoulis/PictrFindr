package com.houlis.haris.feature.details.ui

import com.houlis.haris.core.domain.Picture

sealed interface DetailsUiState {
    data class Fetched(val image: Picture.Image) : DetailsUiState
    object Loading : DetailsUiState
}
