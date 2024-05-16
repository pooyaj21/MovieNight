package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface GetMatchingContentContentsUseCase : UseCase {
    operator fun invoke(): List<Content>?
}

internal class GetMatchingContentContentsUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : GetMatchingContentContentsUseCase {

    override fun invoke(): List<Content>? {
        return contentListRepository.getMatchingList()
    }

}