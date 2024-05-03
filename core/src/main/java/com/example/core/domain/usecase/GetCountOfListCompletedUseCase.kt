package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieListRepository
import com.example.core.domain.UseCase

interface GetCountOfListCompletedUseCase : UseCase {
    operator fun invoke(): Int
}

internal class GetCountOfListCompletedUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : GetCountOfListCompletedUseCase {
    override fun invoke(): Int {
        return movieListRepository.getCountCompletedList()
    }

}