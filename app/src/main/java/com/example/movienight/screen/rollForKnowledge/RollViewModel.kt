package com.example.movienight.screen.rollForKnowledge

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetFirstNameUseCase
import com.example.core.domain.usecase.GetMatchingMoviesUseCase
import com.example.core.domain.usecase.GetSecondNameUseCase
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch

class RollViewModel(
    private val getFirstNameUseCase: GetFirstNameUseCase,
    private val getSecondNameUseCase: GetSecondNameUseCase,
    private val getMatchingMoviesUseCase: GetMatchingMoviesUseCase
) : BaseViewModel<RollTask>() {

    init {
        viewModelScope.launch {
            setTask(
                RollTask.DataCompleted(
                    getFirstNameUseCase(),
                    getSecondNameUseCase(),
                    getMatchingMoviesUseCase()
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