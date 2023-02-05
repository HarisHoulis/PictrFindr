package com.houlis.haris.core.network.data

import com.houlis.haris.core.domain.Picture
import com.houlis.haris.core.domain.PicturesRepositoryContract
import com.houlis.haris.core.network.api.PicturesApi
import com.houlis.haris.core.network.asResult
import com.houlis.haris.core.network.di.ImageBaseUrl
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.mapFailure
import javax.inject.Inject

internal class PicturesRepository @Inject constructor(
    private val api: PicturesApi,
    @ImageBaseUrl
    private val imageBaseUrl: String,
) : PicturesRepositoryContract {

    override suspend fun searchFor(query: String): Result4k<List<Picture>, Nothing?> = asResult {
        api.searchFor(query)
    }
        .map {
            it.toDomain(imageBaseUrl)
        }
        .mapFailure {
            null
        }

}
