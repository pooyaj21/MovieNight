package com.example.movienight.screen.selectMovie

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.*
import com.example.core.model.Movie
import com.example.core.shared.NightResult
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class SelectMovieViewModel(
    private val getListPopularMoviesUseCase: GetListPopularMoviesUseCase,
    private val getFirstNameUseCase: GetFirstNameUseCase,
    private val getFoundedMoviesUseCase: GetFoundedMoviesUseCase,
    private val insertFoundedMoviesListUseCase: InsertFoundedMoviesListUseCase,
    private val getCountOfListCompletedUseCase: GetCountOfListCompletedUseCase,
    private val insertFirstMoviesListUseCase: InsertFirstMoviesListUseCase,
    private val insertSecondMoviesListUseCase: InsertSecondMoviesListUseCase
) : BaseViewModel<SelectMovieTask>() {


    init {
        viewModelScope.launch {
            val founded = getFoundedMoviesUseCase()
            if (founded == null) {
                val page = Random().nextInt(10) + 1
                when (val result = getListPopularMoviesUseCase(page)) {
                    is NightResult.Error.Local -> {}
                    is NightResult.Error.Remote -> {}
                    is NightResult.Success -> {
                        setTask(SelectMovieTask.ListFound(insertFoundedMoviesListUseCase(result.value.list)))
                    }
                }
            } else setTask(SelectMovieTask.ListFound(founded))
        }
    }

    fun listEnded(favorites: List<Movie>) {
        viewModelScope.launch {
            if (getFirstNameUseCase() == null) {
                insertFirstMoviesListUseCase(favorites)
                setTask(SelectMovieTask.GoNext)
            }
            else {
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
}