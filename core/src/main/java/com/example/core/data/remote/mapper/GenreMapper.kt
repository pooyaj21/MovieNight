package com.example.core.data.remote.mapper

import com.example.core.model.Genre
import com.example.core.service.response.GenresListResponse

fun GenresListResponse.GenreResponse.map(): Genre {
    return Genre(
        id = id,
        name = name
    )
}