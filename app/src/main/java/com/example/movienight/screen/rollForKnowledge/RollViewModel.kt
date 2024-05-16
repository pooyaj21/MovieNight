package com.example.movienight.screen.rollForKnowledge

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.*
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch

class RollViewModel(
    private val getFirstNameUseCase: GetFirstNameUseCase,
    private val getSecondNameUseCase: GetSecondNameUseCase,
    private val getMatchingContentContentsUseCase: GetMatchingContentContentsUseCase
) : BaseViewModel<RollTask>() {

    init {
        viewModelScope.launch {
            setTask(
                RollTask.DataCompleted(
                    getFirstNameUseCase(),
                    getSecondNameUseCase(),
                    getMatchingContentContentsUseCase()
                )
            )
        }
    }

    fun movieFounded() {
        viewModelScope.launch {
            setTask(RollTask.MovieFounded)
        }
    }
}