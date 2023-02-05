package com.houlis.haris.test.data

import com.houlis.haris.core.models.PicturesResponseRaw
import com.houlis.haris.core.network.api.PicturesApi
import com.houlis.haris.test.decode
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
