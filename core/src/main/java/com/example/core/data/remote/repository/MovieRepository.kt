package com.example.core.data.remote.repository

import com.example.core.data.remote.extension.mapDataIfSuccess
import com.example.core.data.remote.mapper.map
import com.example.core.model.BaseListModel
import com.example.core.model.Movie
import com.example.core.service.safeApiCall
import com.example.core.service.service.MovieService
import com.example.core.shared.NightResult

interface MovieRepository {

    suspend fun populars(page: Int?): NightResult<BaseListModel<Movie>>

}

internal class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun populars(page: Int?): NightResult<BaseListModel<Movie>> {
        return safeApiCall { movieService.populars(page ?: 1) }
            .mapDataIfSuccess { result -> result.map() }
    }

}