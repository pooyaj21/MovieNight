package com.example.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val genreIds: List<Int>,
    val overview: String,
    val backdropImage: Image?,
    val posterImage: Image?,
    val releaseDate: Date,
    val originalLanguage: String,
    val voteAverage: Double
) : Parcelable