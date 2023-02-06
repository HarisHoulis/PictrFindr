package com.houlis.haris.test.data.provider

import com.houlis.haris.core.models.PicturesResponseRaw
import com.houlis.haris.core.models.PicturesResponseRaw.PicturesRaw
import com.houlis.haris.core.models.PicturesResponseRaw.PicturesRaw.PictureRaw

fun dummyPicturesResponseRaw(picturesRaw: List<PictureRaw> = listOf(dummyPictureRaw1(), dummyPictureRaw2())) =
    PicturesResponseRaw(
        PicturesRaw(picturesRaw)
    )

fun dummyPictureRaw1() = PictureRaw(
    id = "52663187230",
    secret = "3c0b1465a7",
    server = "65535",
    title = "Donat",
)

fun dummyPictureRaw2() = PictureRaw(
    id = "52662247857",
    secret = "acd1bfe3ab",
    server = "65535",
    title = "Donat",
)

fun dummyPictureRaw3() = PictureRaw(
    id = "52663147230",
    secret = "9c0b14s5a7",
    server = "65535",
    title = "Hole",
)

fun dummyPictureRaw4() = PictureRaw(
    id = "52662247857",
    secret = "acdssfe3ab",
    server = "65535",
    title = "Hole",
)
