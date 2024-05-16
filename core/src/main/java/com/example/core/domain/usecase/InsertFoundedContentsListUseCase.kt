package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface InsertFoundedContentsListUseCase : UseCase {
    operator fun invoke(list: List<Content>): List<Content>
}

internal class InsertFoundedContentsListUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : InsertFoundedContentsListUseCase {

    override fun invoke(list: List<Content>): List<Content> {
        return contentListRepository.insertFoundedList(list)
    }

}