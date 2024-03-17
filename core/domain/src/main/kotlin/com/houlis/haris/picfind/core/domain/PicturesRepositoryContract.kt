package com.houlis.haris.picfind.core.domain

import dev.forkhandles.result4k.Result4k

interface PicturesRepositoryContract {

    suspend fun searchFor(query: String): Result4k<List<Picture>, Nothing?>

    suspend fun save(picture: Picture)

    suspend fun retrieve(picId: String): Picture
}
