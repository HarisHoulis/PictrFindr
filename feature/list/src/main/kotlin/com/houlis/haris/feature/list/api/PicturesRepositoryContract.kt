package com.houlis.haris.feature.list.api

import com.houlis.haris.feature.list.domain.Picture
import dev.forkhandles.result4k.Result4k

interface PicturesRepositoryContract {

    suspend fun searchFor(query: String): Result4k<List<Picture>, Nothing?>
}
