package com.houlis.haris.picfind.test

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@OptIn(ExperimentalSerializationApi::class)
inline infix fun <reified T> Json.decode(fileName: String): T =
    decodeFromStream(ClassLoader.getSystemResourceAsStream(fileName))
