package com.houlis.haris.picfind.core.network.data

import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.core.domain.Picture.Title
import com.houlis.haris.picfind.core.models.PicturesResponseRaw

internal fun PicturesResponseRaw.toDomain(imageBaseUrl: String): List<Picture> =
    pictures.picture.map { raw ->
        Picture(
            id = raw.id,
            image = Picture.Image(imageBaseUrl, raw),
            title = Title(raw.title)
        )
    }
