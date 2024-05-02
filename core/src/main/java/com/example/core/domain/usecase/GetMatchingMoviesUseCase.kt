package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieLists
import com.example.core.domain.UseCase
import com.example.core.model.Movie

interface GetMatchingMoviesUseCase : UseCase {
    operator fun invoke(): List<Movie>
}

internal class GetMatchingMoviesUseCaseImpl(
private val movieLists: MovieLists
) : GetMatchingMoviesUseCase  {

    override fun invoke(): List<Movie> {
        return movieLists.firstList.intersect(movieLists.secondList.toSet()).toList()
    }

}