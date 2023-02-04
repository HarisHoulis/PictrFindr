package com.houlis.haris.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.houlis.haris.list.api.PicturesRepositoryContract
import com.houlis.haris.list.ui.PicturesUiState.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PicturesViewModel @Inject constructor(
    private val repository: PicturesRepositoryContract,
) : ViewModel() {

    private val _state: MutableStateFlow<PicturesUiState> = MutableStateFlow(PicturesUiState())
    val state = _state.asStateFlow()

    private val queryFLow = state
        .map { it.input }
        .distinctUntilChanged()

    init {
        viewModelScope.launch {
            queryFLow
                .filterNot(String::isEmpty)
                .distinctUntilChanged()
                .collectLatest { query ->
                    val newState = when (val result = repository.searchFor(query)) {
                        is Failure -> Type.Error
                        is Success ->
                            if (result.value.isEmpty())
                                Type.Empty
                            else
                                Type.Fetched(result.value)
                    }
                    _state.update { it.copy(type = newState) }
                }
        }
    }

    fun searchFor(query: String) {
        _state.update { it.copy(input = query) }
    }
}
