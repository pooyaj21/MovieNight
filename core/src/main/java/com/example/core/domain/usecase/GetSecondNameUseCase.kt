package com.example.core.domain.usecase

import com.example.core.data.local.repository.NamesRepository

interface GetSecondNameUseCase {

    operator fun invoke(): String?
}

internal class GetSecondNameUseCaseImpl(
    private val namesRepository: NamesRepository
) : GetSecondNameUseCase {

    override fun invoke(): String? {
        return namesRepository.getSecondName()
    }
}