package com.example.movienight.screen.selectMovie

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetListPopularMoviesUseCase
import com.example.core.shared.NightResult
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch

class SelectMovieViewModel(
    private val getListPopularMoviesUseCase: GetListPopularMoviesUseCase
) : BaseViewModel<SelectMovieTask>() {

    init {
        viewModelScope.launch {
            when (val result = getListPopularMoviesUseCase(1)) {
                is NightResult.Error.Local -> {
                    println("FUCK Local ${result.message}")
                }
                is NightResult.Error.Remote -> {
                    println("FUCK Remote ${result.message}")
                }
                is NightResult.Success -> {
                    setTask(SelectMovieTask.ListFound(result.value.list))
                }
            }
        }
    }
}