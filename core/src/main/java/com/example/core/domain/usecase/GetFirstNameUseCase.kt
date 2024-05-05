package com.example.core.domain.usecase

import com.example.core.data.local.repository.NamesRepository

interface GetFirstNameUseCase {

    operator fun invoke(): String?
}

internal class GetFirstNameUseCaseImpl(
    private val namesRepository: NamesRepository
) : GetFirstNameUseCase {

    override fun invoke(): String? {
        return namesRepository.getFirstName()
    }
}