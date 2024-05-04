package com.example.movienight.screen.selectMovie

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetCountOfListCompletedUseCase
import com.example.core.domain.usecase.GetListPopularMoviesUseCase
import com.example.core.domain.usecase.InsertFirstMoviesListUseCase
import com.example.core.domain.usecase.InsertSecondMoviesListUseCase
import com.example.core.model.Movie
import com.example.core.shared.NightResult
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class SelectMovieViewModel(
    private val getListPopularMoviesUseCase: GetListPopularMoviesUseCase,
    private val getCountOfListCompletedUseCase: GetCountOfListCompletedUseCase,
    private val insertFirstMoviesListUseCase: InsertFirstMoviesListUseCase,
    private val insertSecondMoviesListUseCase: InsertSecondMoviesListUseCase
) : BaseViewModel<SelectMovieTask>() {

    private var theMovieList: List<Movie>? = null

    init {
        viewModelScope.launch {
            if (theMovieList == null) {
                val page = Random().nextInt(10) + 1
                when (val result = getListPopularMoviesUseCase(page)) {
                    is NightResult.Error.Local -> {}
                    is NightResult.Error.Remote -> {}
                    is NightResult.Success -> {
                        theMovieList = result.value.list
                        setTask(SelectMovieTask.ListFound(result.value.list))
                    }
                }
            } else setTask(SelectMovieTask.ListFound(theMovieList!!))
        }
    }

    fun listEnded(favorites: List<Movie>) {
        viewModelScope.launch {
            when (getCountOfListCompletedUseCase()) {
                0 -> {
                    insertFirstMoviesListUseCase(favorites)
                    setTask(SelectMovieTask.NextList)
                }
                1 -> {
                    insertSecondMoviesListUseCase(favorites)
                    setTask(SelectMovieTask.GoNext)
                }
            }
        }
    }
}