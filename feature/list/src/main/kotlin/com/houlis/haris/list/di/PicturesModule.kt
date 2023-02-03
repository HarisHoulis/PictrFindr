package com.houlis.haris.list.di

import com.houlis.haris.list.api.PicturesRepositoryContract
import com.houlis.haris.list.data.PicturesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface PicturesModule {

    @Binds
    fun bindsRepository(repository: PicturesRepository): PicturesRepositoryContract
}
