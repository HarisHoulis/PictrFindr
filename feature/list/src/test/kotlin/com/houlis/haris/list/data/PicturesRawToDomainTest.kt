package com.houlis.haris.list.data

import com.houlis.haris.list.domain.Picture
import com.houlis.haris.test.data.provider.provider.dummyPictureRawResponse1
import com.houlis.haris.test.data.provider.provider.dummyPictureRawResponse2
import com.houlis.haris.test.data.provider.provider.dummyPicturesResponseRaw
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PicturesRawToDomainTest {

    private companion object {
        private const val IMAGE_BASE_URL = "https://some.url.com/"
    }

    @Test
    fun `maps raw model to domain one`() {
        val result = dummyPicturesResponseRaw().toDomain(IMAGE_BASE_URL)

        result shouldBe expectedDomainModels()
    }

    private fun expectedDomainModels() = listOf(
        Picture(
            id = "52663187230",
            image = Picture.Image(IMAGE_BASE_URL, dummyPictureRawResponse1()),
            title = Picture.Title("Donat")
        ),
        Picture(
            id = "52662247857",
            image = Picture.Image(IMAGE_BASE_URL, dummyPictureRawResponse2()),
            title = Picture.Title("Donat")
        ),
    )
}
