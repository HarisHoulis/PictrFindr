package com.houlis.haris.test.data

import com.houlis.haris.core.domain.Picture
import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.test.data.FakePicturesRepository.Query
import com.houlis.haris.test.domain.provider.dummyPicture1
import com.houlis.haris.test.domain.provider.dummyPicture2
import com.houlis.haris.test.domain.provider.dummyPicture3
import com.houlis.haris.test.domain.provider.dummyPicture4
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success

/**
 * Custom implementation of [PicturesRepositoryContract] for tests,
 * in order to manipulate result of [PicturesRepositoryContract.searchFor].
 *
 * Use one of the [Query] sub-types as a query.
 */
class FakePicturesRepository : PicturesRepositoryContract {

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

    override suspend fun searchFor(query: String): Result4k<List<Picture>, Nothing?> {
        val e = exception
        return when {
            e != null -> Failure(null)
            returnEmptyResponse -> Success(emptyList())
            else -> {
                when (query) {
                    Query.Query1.text -> Success(listOf(dummyPicture1(), dummyPicture2()))
                    Query.Query2.text -> Success(listOf(dummyPicture3(), dummyPicture4()))
                    else -> throw IllegalArgumentException("Have you used the appropriate Query?")
                }
            }
        }
    }

    override suspend fun save(picture: Picture) {
        // NO-OP
    }

    override suspend fun retrieve(picId: String): Picture = dummyPicture1()

    enum class Query(val text: String) {
        Query1("donut"), Query2("hole")
    }
}
