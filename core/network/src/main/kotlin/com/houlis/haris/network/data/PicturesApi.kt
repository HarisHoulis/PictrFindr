package com.houlis.haris.network.data

import com.houlis.haris.network.data.model.PicturesResponseRaw

interface PicturesApi {

    suspend fun searchFor(query: String): PicturesResponseRaw
}

