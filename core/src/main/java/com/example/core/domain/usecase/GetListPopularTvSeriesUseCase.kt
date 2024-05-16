package com.example.core.domain.usecase

import com.example.core.data.remote.repository.TvSeriesRepository
import com.example.core.domain.UseCase
import com.example.core.model.BaseListModel
import com.example.core.model.Content
import com.example.core.shared.DispatcherProvider
import com.example.core.shared.NightResult
import com.example.core.shared.kotlin.suspendSafeDataResult
import kotlinx.coroutines.withContext

interface GetListPopularTvSeriesUseCase : UseCase {

    suspend operator fun invoke(page: Int?): NightResult<BaseListModel<Content.TvSeries>>

}

internal class GetListPopularTvSeriesUseCaseImpl(
    private val tvSeriesRepository: TvSeriesRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetListPopularTvSeriesUseCase {

    override suspend fun invoke(page: Int?): NightResult<BaseListModel<Content.TvSeries>> {
        return withContext(dispatcherProvider.io) {
            suspendSafeDataResult {
                tvSeriesRepository.populars(page)
            }
        }
    }

}