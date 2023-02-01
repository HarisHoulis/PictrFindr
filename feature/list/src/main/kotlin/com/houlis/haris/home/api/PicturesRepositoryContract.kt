package com.houlis.haris.home.api

import com.houlis.haris.home.domain.Picture
import dev.forkhandles.result4k.Result4k

interface PicturesRepositoryContract {

    suspend fun searchFor(query: String): Result4k<Picture, Nothing?>
}
