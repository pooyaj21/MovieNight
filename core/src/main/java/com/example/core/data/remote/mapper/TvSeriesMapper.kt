package com.example.core.data.remote.mapper

import com.example.core.data.remote.extension.ORDER_DATE_FORMAT
import com.example.core.data.remote.extension.toUtcDate
import com.example.core.model.Content
import com.example.core.service.response.ContentResponse

fun ContentResponse.TvSeriesResponse.map(): Content.TvSeries {
    return Content.TvSeries(
        id = id,
        name = name,
        genreIds = genreIds,
        overview = overview,
        backdropImage = backdropImage?.map(),
        posterImage = posterImage?.map(),
        releaseDate = releaseDate.toUtcDate(ORDER_DATE_FORMAT),
        originalLanguage = originalLanguage,
        voteAverage = voteAverage,
    )
}