package com.example.movienight.screen.start

import com.example.core.domain.usecase.InsertContentTypeUseCase
import com.example.core.domain.usecase.InsertNamesUseCase
import com.example.core.model.Content
import com.example.movienight.architect.BaseViewModel

class StartViewModel(
    private val insertNamesUseCase: InsertNamesUseCase,
    private val insertContentTypeUseCase: InsertContentTypeUseCase
) : BaseViewModel<Unit>() {

    fun saveNames(firstName: String, secondName: String) {
        insertNamesUseCase.invoke(firstName, secondName)
    }

    fun saveType(type: Content.Type){
        insertContentTypeUseCase.invoke(type)
    }
}