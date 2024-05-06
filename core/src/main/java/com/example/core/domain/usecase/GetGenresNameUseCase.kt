package com.example.core.domain.usecase

import com.example.core.data.remote.repository.MovieRepository
import com.example.core.domain.UseCase
import com.example.core.model.BaseListModel
import com.example.core.model.Genre
import com.example.core.shared.DispatcherProvider
import com.example.core.shared.NightResult
import com.example.core.shared.kotlin.suspendSafeDataResult
import com.example.core.shared.mapIfSuccess
import kotlinx.coroutines.withContext

interface GetGenresNameUseCase : UseCase {
    suspend operator fun invoke(ids: List<Int>): NightResult<BaseListModel<Genre>>
}

internal class GetGenresNameUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val dispatcherProvider: DispatcherProvider
) : GetGenresNameUseCase {

    override suspend fun invoke(ids: List<Int>): NightResult<BaseListModel<Genre>> {
        return withContext(dispatcherProvider.io) {
            suspendSafeDataResult {
                movieRepository.genres().mapIfSuccess { result ->
                    BaseListModel(result.list.filter { it.id in ids })
                }
            }
        }
    }
}