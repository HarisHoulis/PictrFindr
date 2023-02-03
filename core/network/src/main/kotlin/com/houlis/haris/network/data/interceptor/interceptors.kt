package com.houlis.haris.network.data.interceptor

import com.houlis.haris.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

private const val API_KEY = "YOUR_API_KEY"
private const val PARAMETER_NAME_API_KEY = "api_key"

private const val TAG = "PICTRFINDR_API"

internal val loggingInterceptor = HttpLoggingInterceptor {
    Timber.tag(TAG).d(it)
}.apply {
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


