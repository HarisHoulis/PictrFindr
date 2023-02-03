package com.houlis.haris.list.domain

import com.houlis.haris.test.data.provider.provider.dummyPictureRaw1
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw2
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw3
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw4

internal const val IMAGE_BASE_URL = "someurl.com"

internal fun dummyPictures() = listOf(
    dummyPicture1(),
    dummyPicture2()
)

internal fun dummyPicture1() =
    Picture(
        id = "52663187230",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw1()),
        title = Picture.Title("Donat")
    )

internal fun dummyPicture2() =
    Picture(
        id = "52662247857",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw2()),
        title = Picture.Title("Donat")
    )

internal fun dummyPicture3() =
    Picture(
        id = "52663147230",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw3()),
        title = Picture.Title("Hole")
    )

internal fun dummyPicture4() =
    Picture(
        id = "52662247857",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw4()),
        title = Picture.Title("Hole")
    )
