package com.example.movienight.screen.startConfirmation

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetCountOfListCompletedUseCase
import com.example.core.domain.usecase.GetFirstNameUseCase
import com.example.core.domain.usecase.GetSecondNameUseCase
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch

class NameViewModel(
    private val getCountOfListCompletedUseCase: GetCountOfListCompletedUseCase,
    private val getFirstNameUseCase: GetFirstNameUseCase,
    private val getSecondNameUseCase: GetSecondNameUseCase
) : BaseViewModel<NameTask>() {

    init {
        viewModelScope.launch {
            when (getCountOfListCompletedUseCase()) {
                0 -> setTask(NameTask.ChosenName(getFirstNameUseCase()))
                1 -> setTask(NameTask.ChosenName(getSecondNameUseCase()))
            }
        }
    }
}