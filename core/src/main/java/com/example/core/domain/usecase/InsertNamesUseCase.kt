package com.example.core.domain.usecase

import com.example.core.data.local.repository.NamesRepository

interface InsertNamesUseCase {

    operator fun invoke(firstName: String?, secondName: String?)
}

internal class InsertNamesUseCaseImpl(
    private val namesRepository: NamesRepository
) : InsertNamesUseCase {

    override fun invoke(firstName: String?, secondName: String?) {
        namesRepository.setNames(firstName, secondName)
    }
}