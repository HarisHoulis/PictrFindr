package com.houlis.haris.picfind.core.network.data

import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.test.data.provider.dummyPictureRaw1
import com.houlis.haris.picfind.test.data.provider.dummyPictureRaw2
import com.houlis.haris.picfind.test.data.provider.dummyPicturesResponseRaw
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class PicturesRawToDomainTest {

    private companion object {
        private const val IMAGE_BASE_URL = "https://some.url.com/"
    }

    @Test
    fun `maps raw model to domain one`() {
        val result = dummyPicturesResponseRaw().toDomain(IMAGE_BASE_URL)

        expectThat(result).isEqualTo(expectedDomainModels())
    }

    private fun expectedDomainModels() = listOf(
        Picture(
            id = "52663187230",
            image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw1()),
            title = Picture.Title("Donat")
        ),
        Picture(
            id = "52662247857",
            image = Picture.Image(IMAGE_BASE_URL, dummyPictureRaw2()),
            title = Picture.Title("Donat")
        )
    )
}
