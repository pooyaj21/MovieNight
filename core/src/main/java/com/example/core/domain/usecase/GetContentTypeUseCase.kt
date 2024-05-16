package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface GetContentTypeUseCase : UseCase {
    operator fun invoke(): Content.Type
}

internal class GetContentTypeUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : GetContentTypeUseCase {

    override fun invoke(): Content.Type {
        return contentListRepository.getType()
    }

}