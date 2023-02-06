package com.houlis.haris.core.domain

import com.houlis.haris.test.data.provider.dummyPictureRaw1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PictureImageTest {

    @Test
    fun `creates image`() {
        val imageBaseUrl = "someurl.com/"

        val result = Picture.Image(imageBaseUrl, dummyPictureRaw1())

        result.thumbnail shouldBe "${imageBaseUrl}65535/52663187230_3c0b1465a7_t.jpg"
        result.large shouldBe "${imageBaseUrl}65535/52663187230_3c0b1465a7.jpg"
    }
}
