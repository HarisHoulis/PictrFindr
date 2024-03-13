package com.houlis.haris.core.domain

import com.houlis.haris.test.data.provider.dummyPictureRaw1
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class PictureImageTest {

    @Test
    fun `creates image`() {
        val imageBaseUrl = "someurl.com/"

        val result = Picture.Image(imageBaseUrl, dummyPictureRaw1())

        expectThat(result.thumbnail).isEqualTo("${imageBaseUrl}65535/52663187230_3c0b1465a7_t.jpg")
        expectThat(result.large).isEqualTo("${imageBaseUrl}65535/52663187230_3c0b1465a7.jpg")
    }
}
