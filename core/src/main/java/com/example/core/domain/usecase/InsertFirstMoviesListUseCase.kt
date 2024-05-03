package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieLists
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface InsertFirstMoviesListUseCase : UseCase {
    operator fun invoke(list: List<Movie>): List<Movie>
}

internal class InsertFirstMoviesListUseCaseImpl(
    private val movieLists: MovieLists
) : InsertFirstMoviesListUseCase {

    override fun invoke(list: List<Movie>): List<Movie> {
        movieLists.firstList = list
        return movieLists.firstList
    }

}