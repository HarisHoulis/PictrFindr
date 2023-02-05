package com.houlis.haris.core.domain

import dev.forkhandles.result4k.Result4k

interface PicturesRepositoryContract {

    suspend fun searchFor(query: String): Result4k<List<Picture>, Nothing?>
}
