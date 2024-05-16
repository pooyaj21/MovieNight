package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface GetFoundedContentsUseCase : UseCase {
    operator fun invoke(): List<Content>?
}

internal class GetFoundedContentsUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : GetFoundedContentsUseCase {

    override fun invoke(): List<Content>? {
        return contentListRepository.getFoundedList()
    }

}