package com.houlis.haris.feature.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.feature.details.ui.DetailsUiState.Fetched
import com.houlis.haris.feature.details.ui.DetailsUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: PicturesRepositoryContract) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsUiState> = MutableStateFlow(Loading)
    val uiState = _uiState.asStateFlow()

    fun getImageFor(picId: String) {
        viewModelScope.launch {
            _uiState.update {
                Fetched(repo.retrieve(picId).image)
            }
        }
    }
}
