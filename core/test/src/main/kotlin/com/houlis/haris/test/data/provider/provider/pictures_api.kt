package com.houlis.haris.test.data.provider.provider

import com.houlis.haris.network.data.model.PicturesResponseRaw

fun dummyPicturesResponseRaw() = PicturesResponseRaw(
    PicturesResponseRaw.PicturesRaw(
        listOf(
            dummyPictureRawResponse1(),
            dummyPictureRawResponse2()
        )
    )
)

fun dummyPictureRawResponse1() = PicturesResponseRaw.PicturesRaw.PictureRaw(
    id = "52663187230",
    secret = "3c0b1465a7",
    server = "65535",
    title = "Donat",
)

fun dummyPictureRawResponse2() = PicturesResponseRaw.PicturesRaw.PictureRaw(
    id = "52662247857",
    secret = "acd1bfe3ab",
    server = "65535",
    title = "Donat",
)
