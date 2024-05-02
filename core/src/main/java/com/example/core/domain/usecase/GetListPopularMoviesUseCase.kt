package com.example.core.domain.usecase

import com.example.core.data.remote.repository.MovieRepository
import com.example.core.domain.UseCase
import com.example.core.model.BaseListModel
import com.example.core.model.Movie
import com.example.core.shared.DispatcherProvider
import com.example.core.shared.NightResult
import com.example.core.shared.kotlin.suspendSafeDataResult
import kotlinx.coroutines.withContext

interface GetListPopularMoviesUseCase : UseCase {

    suspend operator fun invoke(page: Int?): NightResult<BaseListModel<Movie>>

}

internal class GetListPopularMoviesUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetListPopularMoviesUseCase {

    override suspend fun invoke(page: Int?): NightResult<BaseListModel<Movie>> {
        return withContext(dispatcherProvider.io) {
            suspendSafeDataResult {
                movieRepository.populars(page)
            }
        }
    }

}