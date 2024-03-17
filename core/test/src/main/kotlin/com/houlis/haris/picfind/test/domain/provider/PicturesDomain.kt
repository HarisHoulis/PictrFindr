package com.houlis.haris.picfind.test.domain.provider

import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.test.data.provider.dummyPictureRaw1
import com.houlis.haris.picfind.test.data.provider.dummyPictureRaw2
import com.houlis.haris.picfind.test.data.provider.dummyPictureRaw3
import com.houlis.haris.picfind.test.data.provider.dummyPictureRaw4

const val IMAGE_BASE_URL = "someurl.com"

fun dummyPictures() = listOf(
    dummyPicture1(),
    dummyPicture2()
)

fun dummyPicture1() =
    Picture(
        id = "dummyPicture1",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw1()),
        title = Picture.Title("Donat")
    )

fun dummyPicture2() =
    Picture(
        id = "dummyPicture2",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw2()),
        title = Picture.Title("Donat")
    )

fun dummyPicture3() =
    Picture(
        id = "dummyPicture3",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw3()),
        title = Picture.Title("Hole")
    )

fun dummyPicture4() =
    Picture(
        id = "dummyPicture4",
        image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw4()),
        title = Picture.Title("Hole")
    )
