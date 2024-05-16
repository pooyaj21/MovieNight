package com.example.core.data.remote.repository

import com.example.core.data.remote.extension.mapDataIfSuccess
import com.example.core.data.remote.extension.mapGenresIfSuccess
import com.example.core.data.remote.mapper.map
import com.example.core.model.BaseListModel
import com.example.core.model.Content
import com.example.core.model.Genre
import com.example.core.service.safeApiCall
import com.example.core.service.service.TvSeriesService
import com.example.core.shared.NightResult

interface TvSeriesRepository {

    suspend fun populars(page: Int?): NightResult<BaseListModel<Content.TvSeries>>

    suspend fun genres(): NightResult<BaseListModel<Genre>>
}

internal class TvSeriesRepositoryImpl(
    private val tvSeriesService: TvSeriesService
) : TvSeriesRepository {

    override suspend fun populars(page: Int?): NightResult<BaseListModel<Content.TvSeries>> {
        return safeApiCall { tvSeriesService.populars(page ?: 1) }
            .mapDataIfSuccess { result -> result.map() }
    }

    override suspend fun genres(): NightResult<BaseListModel<Genre>> {
        return safeApiCall { tvSeriesService.genres() }
            .mapGenresIfSuccess { result -> result.map() }
    }

}