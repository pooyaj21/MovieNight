package com.example.core.domain.usecase

import com.example.core.data.local.repository.ContentListRepository
import com.example.core.domain.UseCase
import com.example.core.model.Content

interface InsertContentTypeUseCase : UseCase {
    operator fun invoke(type: Content.Type): Content.Type
}

internal class InsertContentTypeUseCaseImpl(
    private val contentListRepository: ContentListRepository
) : InsertContentTypeUseCase {

    override fun invoke(type: Content.Type): Content.Type {
        return contentListRepository.insertType(type)
    }

}