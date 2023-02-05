package com.houlis.haris.core.network.api

import com.houlis.haris.core.models.PicturesResponseRaw

interface PicturesApi {

    suspend fun searchFor(query: String): PicturesResponseRaw
}

