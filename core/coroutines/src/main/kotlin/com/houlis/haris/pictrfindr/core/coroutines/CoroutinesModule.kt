package com.houlis.haris.pictrfindr.core.coroutines

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CoroutineModule {
    @Binds
    @Singleton
    fun bindCloseableCoroutineScope(
        closeableCoroutineScope: ProdCloseableCoroutineScope,
    ): CloseableCoroutineScope
}
