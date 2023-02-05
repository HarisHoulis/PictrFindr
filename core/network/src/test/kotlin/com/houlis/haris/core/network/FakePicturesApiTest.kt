package com.houlis.haris.core.network

import com.houlis.haris.test.data.FakePicturesApi
import com.houlis.haris.test.data.provider.provider.dummyPicturesResponseRaw
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

internal class FakePicturesApiTest {

    private val testDispatcher = StandardTestDispatcher()

    private fun testedClass() = FakePicturesApi(testDispatcher, Json { ignoreUnknownKeys = true })

    private val expectedPicturesResponse
        get() = dummyPicturesResponseRaw()

    @Test
    fun `maps api response`() = runTest(testDispatcher) {
        val result = testedClass().searchFor("dog")

        result shouldBe expectedPicturesResponse
    }
}
