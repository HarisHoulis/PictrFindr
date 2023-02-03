package com.houlis.haris.list.data

import com.houlis.haris.list.domain.Picture
import com.houlis.haris.network.data.model.PicturesResponseRaw

fun PicturesResponseRaw.toDomain(imageBaseUrl: String): List<Picture> =
    pictures.picture.map { raw ->
        Picture(
            id = raw.id,
            image = Picture.Image(imageBaseUrl, raw),
            title = Picture.Title(raw.title)
        )
    }
