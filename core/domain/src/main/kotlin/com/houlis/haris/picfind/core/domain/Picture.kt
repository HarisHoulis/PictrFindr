package com.houlis.haris.picfind.core.domain

import com.houlis.haris.picfind.core.models.PicturesResponseRaw.PicturesRaw.PictureRaw
import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    val id: String,
    val image: Image,
    val title: Title,
) {

    @Serializable
    @JvmInline
    value class Image private constructor(private val basePath: String) {

        companion object {
            private const val FORWARD_SLASH = "/"
            private const val UNDERSCORE = "_"
            private const val THUMBNAIL_SUFFIX = "t"
            private const val JPG_EXT = ".jpg"

            operator fun invoke(baseUrl: String, pictureRaw: PictureRaw) = Image(
                buildString {
                    append(baseUrl)
                    append(pictureRaw.server)
                    append(FORWARD_SLASH)
                    append(pictureRaw.id)
                    append(UNDERSCORE)
                    append(pictureRaw.secret)
                }
            )
        }

        val thumbnail: String
            get() = buildString {
                append(basePath)
                append(UNDERSCORE)
                append(THUMBNAIL_SUFFIX)
                append(JPG_EXT)
            }

        val large: String
            get() = buildString {
                append(basePath)
                append(JPG_EXT)
            }
    }

    @Serializable
    @JvmInline
    value class Title(val value: String)
}
