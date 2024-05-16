package com.example.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
open class Content(
    open val id: Int,
    open val name: String,
    open val genreIds: List<Int>,
    open val overview: String,
    open val backdropImage: Image?,
    open val posterImage: Image?,
    open val releaseDate: Date,
    open val originalLanguage: String,
    open val voteAverage: Double
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Content

        if (id != other.id) return false
        if (name != other.name) return false
        if (genreIds != other.genreIds) return false
        if (overview != other.overview) return false
        if (backdropImage != other.backdropImage) return false
        if (posterImage != other.posterImage) return false
        if (releaseDate != other.releaseDate) return false
        if (originalLanguage != other.originalLanguage) return false
        if (voteAverage != other.voteAverage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + genreIds.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + (backdropImage?.hashCode() ?: 0)
        result = 31 * result + (posterImage?.hashCode() ?: 0)
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + originalLanguage.hashCode()
        result = 31 * result + voteAverage.hashCode()
        return result
    }

    override fun toString(): String {
        return "Content(id=$id, name='$name', genreIds=$genreIds, overview='$overview', backdropImage=$backdropImage, posterImage=$posterImage, releaseDate=$releaseDate, originalLanguage='$originalLanguage', voteAverage=$voteAverage)"
    }

    @Parcelize
    data class Movie(
        override val id: Int,
        override val name: String,
        override val genreIds: List<Int>,
        override val overview: String,
        override val backdropImage: Image?,
        override val posterImage: Image?,
        override val releaseDate: Date,
        override val originalLanguage: String,
        override val voteAverage: Double
    ) : Content(
        id = id,
        name = name,
        genreIds = genreIds,
        overview = overview,
        backdropImage = backdropImage,
        posterImage = posterImage,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage
    )

    @Parcelize
    data class TvSeries(
        override val id: Int,
        override val name: String,
        override val genreIds: List<Int>,
        override val overview: String,
        override val backdropImage: Image?,
        override val posterImage: Image?,
        override val releaseDate: Date,
        override val originalLanguage: String,
        override val voteAverage: Double
    ) : Content(
        id = id,
        name = name,
        genreIds = genreIds,
        overview = overview,
        backdropImage = backdropImage,
        posterImage = posterImage,
        releaseDate = releaseDate,
        originalLanguage = originalLanguage,
        voteAverage = voteAverage
    )
}