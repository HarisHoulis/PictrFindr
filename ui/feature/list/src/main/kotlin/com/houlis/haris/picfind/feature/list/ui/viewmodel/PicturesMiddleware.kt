package com.houlis.haris.picfind.feature.list.ui.viewmodel

import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.core.domain.PicturesRepositoryContract
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.Error
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.NoResults
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.PicturesLoaded
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.picfind.ui.common.mvi.Dispatcher
import com.houlis.haris.picfind.ui.common.mvi.Middleware
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Success
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.debounce
import java.time.Duration

internal class PicturesMiddleware(
    private val repository: PicturesRepositoryContract,
    private val inputDebounce: Duration,
    dispatcher: Dispatcher<PicturesAction>,
    scope: CloseableCoroutineScope,
) : Middleware<PicturesState, PicturesAction>(dispatcher, scope) {

    private val searchFlow = MutableStateFlow("")

    init {
        scope.launch { initSearchFlow() }
    }

    override suspend fun process(state: PicturesState, action: PicturesAction) {
        when (action) {
            is SearchFor -> searchFlow.emit(action.query)
            else -> {}
        }
    }

    private suspend fun initSearchFlow() {
        searchFlow
            .debounce(inputDebounce)
            .collect { query ->
                if (query.isNotBlank()) {
                    val action = when (val result = repository.searchFor(query)) {
                        is Failure -> Error
                        is Success -> result.mapToAction()
                    }
                    dispatch(action)
                }
            }
    }

    private fun Success<List<Picture>>.mapToAction() = when {
        value.isEmpty() -> NoResults
        else -> PicturesLoaded(value.toImmutableList())
    }
}
