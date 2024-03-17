package com.houlis.haris.picfind.core.network.api

import com.houlis.haris.picfind.core.models.PicturesResponseRaw

interface PicturesApi {

    suspend fun searchFor(query: String): PicturesResponseRaw
}
