package com.example.movienight.screen.selectMovie

import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.*
import com.example.core.model.Content
import com.example.core.shared.NightResult
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class SelectContentViewModel(
    private val getListPopularMoviesUseCase: GetListPopularMoviesUseCase,
    private val getFirstNameUseCase: GetFirstNameUseCase,
    private val getFoundedContentsUseCase: GetFoundedContentsUseCase,
    private val insertFoundedContentsListUseCase: InsertFoundedContentsListUseCase,
    private val getCountOfListCompletedUseCase: GetCountOfListCompletedUseCase,
    private val insertFirstContentsListUseCase: InsertFirstContentsListUseCase,
    private val insertSecondContentsListUseCase: InsertSecondContentsListUseCase
) : BaseViewModel<SelectContentTask>() {


    init {
        viewModelScope.launch {
            val founded = getFoundedContentsUseCase()
            if (founded == null) {
                val page = Random().nextInt(10) + 1
                when (val result = getListPopularMoviesUseCase(page)) {
                    is NightResult.Error.Local -> {}
                    is NightResult.Error.Remote -> {}
                    is NightResult.Success -> {
                        setTask(SelectContentTask.ListFound(insertFoundedContentsListUseCase(result.value.list)))
                    }
                }
            } else setTask(SelectContentTask.ListFound(founded))
        }
    }

    fun listEnded(favorites: List<Content>) {
        viewModelScope.launch {
            if (getFirstNameUseCase() == null) {
                insertFirstContentsListUseCase(favorites)
                setTask(SelectContentTask.GoNext)
            }
            else {
                when (getCountOfListCompletedUseCase()) {
                    0 -> {
                        insertFirstContentsListUseCase(favorites)
                        setTask(SelectContentTask.NextList)
                    }
                    1 -> {
                        insertSecondContentsListUseCase(favorites)
                        setTask(SelectContentTask.GoNext)
                    }
                }
            }
        }
    }
}