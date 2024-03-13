package com.houlis.haris.feature.details.ui

import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.pictrfindr.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.pictrfindr.ui.common.mvi.MwProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object DetailsModule {

    @ViewModelScoped
    @Provides
    fun providesMiddlewares(
        repository: PicturesRepositoryContract,
        scope: CloseableCoroutineScope,
    ): MwProvider<DetailsState, PicDetailsAction> =
        MwProvider { dispatcher ->
            listOf(
                DetailsMiddleware(repository, dispatcher, scope)
            )
        }
}
