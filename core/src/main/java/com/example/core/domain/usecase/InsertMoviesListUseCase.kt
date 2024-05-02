package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieLists
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface InsertMoviesListUseCase : UseCase {
    operator fun invoke(list: List<Movie>): List<Movie>
}

internal class InsertMoviesListUseCaseImpl(
    private val movieLists: MovieLists
) : InsertMoviesListUseCase {

    override fun invoke(list: List<Movie>): List<Movie> {
        return if (movieLists.firstList.isEmpty()) {
            movieLists.firstList = list
            movieLists.firstList
        } else {
            movieLists.secondList = list
            movieLists.secondList
        }
    }

}