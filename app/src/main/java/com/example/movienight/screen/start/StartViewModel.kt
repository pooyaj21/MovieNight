package com.example.movienight.screen.start

import com.example.core.domain.usecase.InsertNamesUseCase
import com.example.movienight.architect.BaseViewModel

class StartViewModel(
    private val insertNamesUseCase: InsertNamesUseCase
) : BaseViewModel<Unit>() {

    fun saveNames(firstName: String, secondName: String) {
        insertNamesUseCase.invoke(firstName, secondName)
    }
}