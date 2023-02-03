package com.houlis.haris.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houlis.haris.list.api.PicturesRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PicturesViewModel @Inject constructor(
    val repository: PicturesRepositoryContract,
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state: MutableStateFlow<PicturesUiState> = MutableStateFlow(PicturesUiState.Loading)
    val state = _state.asStateFlow()

    fun searchFor(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            val newState = when (val result = repository.searchFor(query)) {
                is Failure -> PicturesUiState.Error
                is Success ->
                    if (result.value.isEmpty())
                        PicturesUiState.Empty
                    else
                        PicturesUiState.Fetched(result.value)
            }
            _state.update { newState }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
        searchJob = null
    }
}
