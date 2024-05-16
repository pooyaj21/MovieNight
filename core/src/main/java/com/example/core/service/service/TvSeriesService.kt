package com.example.core.service.service

import com.example.core.service.response.BaseListResponse
import com.example.core.service.response.ContentResponse
import com.example.core.service.response.GenresListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvSeriesService {

    @GET("tv/popular?language=en-US")
    suspend fun populars(@Query("page") page: Int): BaseListResponse<ContentResponse.TvSeriesResponse>

    @GET("genre/tv/list?language=en")
    suspend fun genres(): GenresListResponse
}