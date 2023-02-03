package com.houlis.haris.network.di

import com.houlis.haris.network.data.ApiProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ImageBaseUrl

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun providesApi(apiProvider: ApiProvider) = apiProvider.api

    @ImageBaseUrl
    @Provides
    fun providesImageBaseUrl() = "https://live.staticflickr.com/"
}
