package com.houlis.haris.feature.list.data

import com.houlis.haris.feature.list.api.PicturesRepositoryContract
import com.houlis.haris.feature.list.domain.Picture
import com.houlis.haris.network.asResult
import com.houlis.haris.network.data.PicturesApi
import com.houlis.haris.network.di.ImageBaseUrl
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
