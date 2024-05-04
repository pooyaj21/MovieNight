package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface GetFoundedMoviesUseCase : UseCase {
    operator fun invoke(): List<Movie>?
}

internal class GetFoundedMoviesUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : GetFoundedMoviesUseCase {

    override fun invoke(): List<Movie>? {
        return movieListRepository.getFoundedList()
    }

}