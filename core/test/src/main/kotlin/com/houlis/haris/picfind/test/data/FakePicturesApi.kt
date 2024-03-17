package com.houlis.haris.picfind.test.data

import com.houlis.haris.picfind.core.models.PicturesResponseRaw
import com.houlis.haris.picfind.core.network.api.PicturesApi
import com.houlis.haris.picfind.test.decode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class FakePicturesApi(private val ioDispatcher: CoroutineDispatcher, private val json: Json) : PicturesApi {

    private companion object {
        private const val PICTURES_RESPONSE_FILE = "pictures_response.json"
    }

    override suspend fun searchFor(query: String): PicturesResponseRaw =
        withContext(ioDispatcher) {
            json decode PICTURES_RESPONSE_FILE
        }
}
