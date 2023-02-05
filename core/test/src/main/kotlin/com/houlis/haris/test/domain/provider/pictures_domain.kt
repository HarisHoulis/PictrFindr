package com.houlis.haris.test.domain.provider

import com.houlis.haris.core.domain.Picture
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw1
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw2
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw3
import com.houlis.haris.test.data.provider.provider.dummyPictureRaw4

const val IMAGE_BASE_URL = "someurl.com"

fun dummyPictures() = listOf(
    dummyPicture1(),
    dummyPicture2()
)

fun dummyPicture1() =
    Picture(
        id = "52663187230",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw1()),
        title = Picture.Title("Donat")
    )

fun dummyPicture2() =
    Picture(
        id = "52662247857",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw2()),
        title = Picture.Title("Donat")
    )

fun dummyPicture3() =
    Picture(
        id = "52663147230",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw3()),
        title = Picture.Title("Hole")
    )

fun dummyPicture4() =
    Picture(
        id = "52662247857",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw4()),
        title = Picture.Title("Hole")
    )
