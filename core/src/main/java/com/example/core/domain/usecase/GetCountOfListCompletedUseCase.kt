package com.example.core.domain.usecase

import com.example.core.data.local.repository.MovieLists
import com.example.core.domain.UseCase

interface GetCountOfListCompletedUseCase : UseCase {
    operator fun invoke(): Int
}

internal class GetCountOfListCompletedUseCaseImpl(
    private val movieLists: MovieLists
) : GetCountOfListCompletedUseCase {
    override fun invoke(): Int {
        var count = 0
        if (movieLists.firstList.isNotEmpty()) count++
        if (movieLists.secondList.isNotEmpty()) count++
        return count
    }

}