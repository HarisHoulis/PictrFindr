package com.houlis.haris.core.network.di

import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.core.network.data.PicturesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsRepository(repository: PicturesRepository): PicturesRepositoryContract
}
