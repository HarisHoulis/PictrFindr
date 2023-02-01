package com.houlis.haris.network.api

import com.houlis.haris.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor(json: Json) {

    private companion object {
        private const val BASE_URL = ""
        private const val TAG = "PICTRFINDR_API"
    }

    private val interceptor = HttpLoggingInterceptor {
        Timber.tag(TAG).d(it)
    }.apply {
        level = when {
            BuildConfig.DEBUG -> BODY
            else -> NONE
        }
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    private val converterFactory = json.asConverterFactory("application/json".toMediaType())

    val api: PicturesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
        .create(PicturesApi::class.java)
}
