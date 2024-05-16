package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface InsertSecondContentsListUseCase : UseCase {
    operator fun invoke(list: List<Content>): List<Content>
}

internal class InsertSecondContentsListUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : InsertSecondContentsListUseCase {

    override fun invoke(list: List<Content>): List<Content> {
        return contentListRepository.insertSecondList(list)
    }

}