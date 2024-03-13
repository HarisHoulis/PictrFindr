package com.houlis.haris.feature.details.ui

import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.feature.details.ui.PicDetailsAction.DetailsFetched
import com.houlis.haris.feature.details.ui.PicDetailsAction.FetchDetailsFor
import com.houlis.haris.pictrfindr.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.pictrfindr.ui.common.mvi.Dispatcher
import com.houlis.haris.pictrfindr.ui.common.mvi.Middleware

internal class DetailsMiddleware(
    private val repository: PicturesRepositoryContract,
    dispatcher: Dispatcher<PicDetailsAction>,
    scope: CloseableCoroutineScope,
) : Middleware<DetailsState, PicDetailsAction>(dispatcher, scope) {

    override suspend fun process(state: DetailsState, action: PicDetailsAction) {
        when (action) {
            is FetchDetailsFor -> dispatch(DetailsFetched(repository.retrieve(action.imageId).image))
            else -> {}
        }
    }
}
