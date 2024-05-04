package com.example.movienight.screen.startConfirmation

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetCountOfListCompletedUseCase
import com.example.movienight.architect.BaseViewModel
import com.example.movienight.screen.start.StartFragment
import kotlinx.coroutines.launch

class NameViewModel(
    private val getCountOfListCompletedUseCase: GetCountOfListCompletedUseCase
) : BaseViewModel<NameTask>() {

    init {
        viewModelScope.launch {
            when (getCountOfListCompletedUseCase()) {
                0 -> setTask(NameTask.ChosenName(StartFragment.FIRST_NAME))
                1 -> setTask(NameTask.ChosenName(StartFragment.SECOND_NAME))
            }
        }
    }
}