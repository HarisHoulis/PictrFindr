package com.houlis.haris.feature.list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.time.Duration

@Module
@InstallIn(ViewModelComponent::class)
internal interface PicturesModule {

    companion object {
        @Provides
        fun providesInputDebounceDuration(): Duration = Duration.ofMillis(500)
    }
}
