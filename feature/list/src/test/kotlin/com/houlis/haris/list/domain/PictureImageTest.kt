package com.houlis.haris.list.domain

import com.houlis.haris.test.data.provider.provider.dummyPictureRawResponse1
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PictureImageTest {

    @Test
    fun `creates image`() {
        val imageBaseUrl = "someurl.com/"

        val result = Picture.Image(imageBaseUrl, dummyPictureRawResponse1())

        result.value shouldBe "${imageBaseUrl}65535/52663187230_3c0b1465a7_t.jpg"
    }
}
