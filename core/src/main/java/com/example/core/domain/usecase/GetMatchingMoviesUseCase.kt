package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface GetMatchingMoviesUseCase : UseCase {
    operator fun invoke(): List<Movie>?
}

internal class GetMatchingMoviesUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : GetMatchingMoviesUseCase {

    override fun invoke(): List<Movie>? {
        return movieListRepository.getMatchingList()
    }

}