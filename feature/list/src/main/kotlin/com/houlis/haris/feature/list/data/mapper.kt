package com.houlis.haris.feature.list.data

import com.houlis.haris.feature.list.domain.Picture
import com.houlis.haris.network.data.model.PicturesResponseRaw

internal fun PicturesResponseRaw.toDomain(imageBaseUrl: String): List<Picture> =
    pictures.picture.map { raw ->
        Picture(
            id = raw.id,
            image = Picture.Image(imageBaseUrl, raw),
            title = Picture.Title(raw.title)
        )
    }
