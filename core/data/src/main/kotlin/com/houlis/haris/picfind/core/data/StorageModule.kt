package com.houlis.haris.picfind.core.data

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

fun interface SaveState<T> : (String, T) -> Unit

fun interface ReadState<T> : (String) -> T

/**
 * Type parameters are NOT supported: [see](https://github.com/google/dagger/issues/1757)
 */
@Module
@InstallIn(ViewModelComponent::class)
internal object StorageModule {

    @ViewModelScoped
    @Provides
    fun providesSaveStateHandle(savedStateHandle: SavedStateHandle): SaveState<String> =
        SaveState { key, value ->
            savedStateHandle[key] = value
        }

    @ViewModelScoped
    @Provides
    fun providesReadStateHandle(savedStateHandle: SavedStateHandle): ReadState<String> =
        ReadState { key ->
            checkNotNull(savedStateHandle[key]) as String
        }
}
