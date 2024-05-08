package com.example.movienight.screen.movieDetail

import com.example.core.model.Genre
import com.example.core.model.Image
import com.example.core.model.Movie

data class UiMovie(
    val id: Int,
    val title: String,
    val genres: List<Genre>,
    val overview: String,
    val backdropImage: Image?,
    val posterImage: Image?,
    val voteAverage: Double
)

fun Movie.toUiMovie(genres: List<Genre>): UiMovie {
    return UiMovie(
        id = id,
        title = title,
        genres = genres,
        overview = overview,
        backdropImage = backdropImage,
        posterImage = posterImage,
        voteAverage = voteAverage
    )
}