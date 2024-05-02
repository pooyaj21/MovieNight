package com.example.movienight.architect

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<Task>:ViewModel() {
    private val mutableTaskFlow by lazy { MutableSharedFlow<Task>() }
    val taskFlow by lazy { mutableTaskFlow.asSharedFlow() }

    protected suspend fun setTask(task: Task) = mutableTaskFlow.emit(task)
}