package com.example.core.service.service

import com.example.core.service.response.BaseListResponse
import com.example.core.service.response.GenresListResponse
import com.example.core.service.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?language=en-US")
    suspend fun populars(@Query("page") page: Int): BaseListResponse<MovieResponse>

    @GET("genre/movie/list?language=en")
    suspend fun genres(): GenresListResponse
}