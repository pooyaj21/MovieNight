package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface InsertFirstMoviesListUseCase : UseCase {
    operator fun invoke(list: List<Movie>): List<Movie>?
}

internal class InsertFirstMoviesListUseCaseImpl(
    private val movieListRepository: MovieListRepository
) : InsertFirstMoviesListUseCase {

    override fun invoke(list: List<Movie>): List<Movie>? {
        return movieListRepository.insertFirstList(list)
    }

}