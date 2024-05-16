package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface InsertFirstContentsListUseCase : UseCase {
    operator fun invoke(list: List<Content>): List<Content>
}

internal class InsertFirstContentsListUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : InsertFirstContentsListUseCase {

    override fun invoke(list: List<Content>): List<Content> {
        return contentListRepository.insertFirstList(list)
    }

}