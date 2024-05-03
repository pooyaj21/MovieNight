package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieLists
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface InsertSecondMoviesListUseCase : UseCase {
    operator fun invoke(list: List<Movie>): List<Movie>
}

internal class InsertSecondMoviesListUseCaseImpl(
    private val movieLists: MovieLists
) : InsertSecondMoviesListUseCase {

    override fun invoke(list: List<Movie>): List<Movie> {
        movieLists.secondList = list
        return movieLists.secondList
    }

}