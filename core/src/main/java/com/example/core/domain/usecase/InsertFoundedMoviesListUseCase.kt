package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface InsertFoundedMoviesListUseCase : UseCase {
    operator fun invoke(list: List<Movie>): List<Movie>
}

internal class InsertFoundedMoviesListUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : InsertFoundedMoviesListUseCase {

    override fun invoke(list: List<Movie>): List<Movie> {
        return movieListRepository.insertFoundedList(list)
    }

}