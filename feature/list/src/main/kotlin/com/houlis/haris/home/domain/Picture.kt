package com.houlis.haris.home.domain

data class Picture(
    private val id: String,
    val image: Image,
    val title: Title,
) {
    @JvmInline
    value class Image(val value: String)

    @JvmInline
    value class Title(val value: String)
}
