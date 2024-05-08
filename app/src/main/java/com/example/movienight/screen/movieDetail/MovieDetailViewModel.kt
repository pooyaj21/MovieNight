package com.example.movienight.screen.movieDetail

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetGenresNameUseCase
import com.example.core.shared.NightResult
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getGenresNameUseCase: GetGenresNameUseCase
) : BaseViewModel<MovieDetailTask>() {

    fun getGenres(genreIds: List<Int>) {
        viewModelScope.launch {
            when (val result = getGenresNameUseCase.invoke(genreIds)) {
                is NightResult.Error.Local -> {}
                is NightResult.Error.Remote -> {}
                is NightResult.Success -> {
                    setTask(MovieDetailTask.GenresFound(result.value.list))
                }
            }
        }
    }
}