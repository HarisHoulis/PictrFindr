package com.houlis.haris.picfind.feature.list.ui.viewmodel

import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.picfind.core.domain.PicturesRepositoryContract
import com.houlis.haris.picfind.ui.common.mvi.MwProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.Duration

@Module
@InstallIn(ViewModelComponent::class)
internal object PicturesModule {

    @Provides
    @ViewModelScoped
    fun providesInputDebounceDuration(): Duration = Duration.ofMillis(500)

    @ViewModelScoped
    @Provides
    fun providesMiddlewares(
        repository: PicturesRepositoryContract,
        debounce: Duration,
        scope: CloseableCoroutineScope,
    ): MwProvider<PicturesState, PicturesAction> =
        MwProvider { dispatcher ->
            listOf(
                PicturesMiddleware(repository, debounce, dispatcher, scope)
            )
        }
}
