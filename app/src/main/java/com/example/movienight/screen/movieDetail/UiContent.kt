package com.example.movienight.screen.movieDetail

import com.example.core.model.Content
import com.example.core.model.Genre
import com.example.core.model.Image

data class UiContent(
    val id: Int,
    val name: String,
    val genres: List<Genre>,
    val overview: String,
    val backdropImage: Image?,
    val posterImage: Image?,
    val voteAverage: Double
)

fun Content.toUiContent(genres: List<Genre>): UiContent {
    return UiContent(
        id = id,
        name = name,
        genres = genres,
        overview = overview,
        backdropImage = backdropImage,
        posterImage = posterImage,
        voteAverage = voteAverage
    )
}