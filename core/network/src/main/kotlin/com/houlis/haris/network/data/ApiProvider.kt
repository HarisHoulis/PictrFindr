package com.houlis.haris.network.data

import com.houlis.haris.network.data.interceptor.loggingInterceptor
import com.houlis.haris.network.data.interceptor.tokenInterceptor
import com.houlis.haris.network.data.model.PicturesResponseRaw
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The production version of [PicturesApi], provided by [ApiProvider]
 */
private interface RetrofitPicturesApi : PicturesApi {

    @GET("?method=flickr.photos.search&format=json&per_page=5&nojsoncallback=1")
    override suspend fun searchFor(
        @Query("text") query: String,
    ): PicturesResponseRaw
}

/**
 * Helper class to provide the production version of [PicturesApi]
 */
@Singleton
internal class ApiProvider @Inject constructor(json: Json) {

    private companion object {
        private const val BASE_URL = "https://api.flickr.com/services/rest/"
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    private val converterFactory = json.asConverterFactory("application/json".toMediaType())

    val api: PicturesApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
        .create(RetrofitPicturesApi::class.java)
}
