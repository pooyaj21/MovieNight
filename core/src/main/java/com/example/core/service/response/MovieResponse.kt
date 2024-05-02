package com.example.core.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("genre_ids") val genreIds: List<Int>,
    @SerialName("overview") val overview: String,
    @SerialName("backdrop_path") val backdropImage: String?,
    @SerialName("poster_path") val posterImage: String?,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("vote_average") val voteAverage: Double
)
