package com.example.core.data.remote.mapper

import com.example.core.model.Image

fun String.map(): Image {
    return Image(url = this)
}