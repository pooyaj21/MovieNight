package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase

interface GetCountOfListCompletedUseCase : UseCase {
    operator fun invoke(): Int
}

internal class GetCountOfListCompletedUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : GetCountOfListCompletedUseCase {
    override fun invoke(): Int {
        return contentListRepository.getCountCompletedList()
    }

}