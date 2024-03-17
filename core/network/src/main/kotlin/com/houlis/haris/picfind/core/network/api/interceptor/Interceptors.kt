package com.houlis.haris.picfind.core.network.api.interceptor

import com.houlis.haris.picfind.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

private const val API_KEY = BuildConfig.API_KEY
private const val PARAMETER_NAME_API_KEY = "api_key"

internal val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = when {
        BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
        else -> HttpLoggingInterceptor.Level.NONE
    }
}

internal val tokenInterceptor = Interceptor { chain ->
    val original = chain.request()

    val urlWithApiKey = original
        .url
        .newBuilder()
        .apply {
            addQueryParameter(PARAMETER_NAME_API_KEY, API_KEY)
        }
        .build()

    val newRequest = original
        .newBuilder()
        .url(urlWithApiKey)
        .build()

    chain.proceed(newRequest)
}
