package com.example.core.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class ContentResponse {

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
    ) : ContentResponse()

    @Serializable
    data class TvSeriesResponse(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String,
        @SerialName("genre_ids") val genreIds: List<Int>,
        @SerialName("overview") val overview: String,
        @SerialName("backdrop_path") val backdropImage: String?,
        @SerialName("poster_path") val posterImage: String?,
        @SerialName("first_air_date") val releaseDate: String,
        @SerialName("original_language") val originalLanguage: String,
        @SerialName("vote_average") val voteAverage: Double
    ) : ContentResponse()
}