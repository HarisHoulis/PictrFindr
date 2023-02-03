package com.houlis.haris.test.data

import com.houlis.haris.network.data.PicturesApi
import com.houlis.haris.network.data.model.PicturesResponseRaw
import com.houlis.haris.network.data.model.PicturesResponseRaw.PicturesRaw
import com.houlis.haris.test.data.TestPicturesApi.Query
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw3
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw4
import com.houlis.haris.test.data.provider.provider.dummyPicturesResponseRaw

/**
 * Custom implementation of [PicturesApi] for tests, in order to manipulate result of [PicturesApi.searchFor].
 *
 * Use one of [Query] sub-types as a query.
 */
class TestPicturesApi : PicturesApi {

    private var exception: Exception? = null

    private var returnEmptyResponse: Boolean = false

    fun setEmptyResponse() {
        exception = null
        returnEmptyResponse = true
    }

    fun throwException() {
        returnEmptyResponse = false
        exception = UnsupportedOperationException()
    }

    override suspend fun searchFor(query: String): PicturesResponseRaw {
        val e = exception
        return when {
            e != null -> throw e
            returnEmptyResponse -> PicturesResponseRaw(PicturesRaw(emptyList()))
            else -> {
                when (query) {
                    Query.Query1.text -> dummyPicturesResponseRaw()
                    Query.Query2.text -> dummyPicturesResponseRaw(listOf(dummyPictureRaw3(), dummyPictureRaw4()))
                    else -> throw IllegalArgumentException("Have you used the appropriate Query?")
                }
            }
        }
    }

    enum class Query(val text: String) {
        Query1("donut"), Query2("hole")
    }

}
