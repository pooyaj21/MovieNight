package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface InsertSecondMoviesListUseCase : UseCase {
    operator fun invoke(list: List<Movie>): List<Movie>?
}

internal class InsertSecondMoviesListUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : InsertSecondMoviesListUseCase {

    override fun invoke(list: List<Movie>): List<Movie>? {
        return movieListRepository.insertSecondList(list)
    }

}