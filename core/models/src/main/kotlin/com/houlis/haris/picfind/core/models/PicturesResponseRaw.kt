package com.houlis.haris.picfind.core.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PicturesResponseRaw(
    @SerialName("photos")
    val pictures: PicturesRaw,
) {

    @Serializable
    data class PicturesRaw(
        @SerialName("photo")
        val picture: List<PictureRaw>,
    ) {

        @Serializable
        data class PictureRaw(
            @SerialName("id")
            val id: String,
            @SerialName("secret")
            val secret: String,
            @SerialName("server")
            val server: String,
            @SerialName("title")
            val title: String,
        )
    }
}
