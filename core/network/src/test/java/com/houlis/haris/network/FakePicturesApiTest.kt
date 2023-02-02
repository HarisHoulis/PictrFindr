package com.houlis.haris.network

import com.houlis.haris.network.data.model.PicturesResponseRaw
import com.houlis.haris.network.data.model.PicturesResponseRaw.PicturesRaw
import com.houlis.haris.network.data.model.PicturesResponseRaw.PicturesRaw.PictureRaw
import com.houlis.haris.test.data.FakePicturesApi
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

class FakePicturesApiTest {

    private val testDispatcher = StandardTestDispatcher()

    private fun testedClass() = FakePicturesApi(testDispatcher, Json { ignoreUnknownKeys = true })

    @Test
    fun `maps api response`() = runTest(testDispatcher) {
        val result = testedClass().searchFor("dog")

        result shouldBe expectedPicturesResponse()
    }

    private fun expectedPicturesResponse() = PicturesResponseRaw(
        PicturesRaw(
            listOf(
                PictureRaw(
                    id = "52663187230",
                    secret = "3c0b1465a7",
                    server = "65535",
                    title = "Donat",
                ),
                PictureRaw(
                    id = "52662247857",
                    secret = "acd1bfe3ab",
                    server = "65535",
                    title = "Donat",
                )
            )
        )
    )
}
