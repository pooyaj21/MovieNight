package com.example.movienight.screen.selectMovie

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetListPopularMoviesUseCase
import com.example.core.shared.NightResult
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class SelectMovieViewModel(
    private val getListPopularMoviesUseCase: GetListPopularMoviesUseCase
) : BaseViewModel<SelectMovieTask>() {

    init {
        viewModelScope.launch {
            val page = Random().nextInt(10) + 1
            when (val result = getListPopularMoviesUseCase(page)) {
                is NightResult.Error.Local -> {}
                is NightResult.Error.Remote -> {}
                is NightResult.Success -> {
                    setTask(SelectMovieTask.ListFound(result.value.list))
                }
            }
        }
    }
}