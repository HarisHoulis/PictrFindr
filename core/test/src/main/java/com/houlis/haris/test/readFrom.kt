package com.houlis.haris.test

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.IOException
import kotlin.reflect.KClass

inline infix fun <reified T : Any> KClass<T>.from(fileName: String): T = readInput(fileName)

val json = Json { ignoreUnknownKeys = true }

@Throws(IOException::class)
inline fun <reified T> readInput(fileName: String): T =
    json.decodeFromStream(ClassLoader.getSystemResourceAsStream(fileName))
